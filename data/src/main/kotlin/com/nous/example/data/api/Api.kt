package com.nous.example.data.api

import com.nous.example.domain.model.Data
import com.nous.example.domain.model.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class Api {

    /**
     * Calls [callApi] and processes response using [parseDto] synchronously.
     *
     * Returns [Data.Success] with [MODEL] when every is fine.
     * Returns [Data.Error] with [NetworkErrorException] when request was not proceed due to network error (eq. offline mode)
     * Returns [Data.Error] with [ResponseParseException] when some error occurred during parsing response body
     * Returns [Data.Error] with [NoResponseBodyException] when request response doesn't contain any body (eq. body is null)
     *
     * Note: [callApi] and [parseDto] should throw exception when some error occurs.
     */
    suspend fun <DTO, MODEL> request(
        callApi: suspend () -> Response<DTO>,
        parseDto: suspend (DTO.() -> MODEL)
    ): ResultData<MODEL> {
        val response = when (val result = executeRequest(callApi)) {
            is Data.Success -> result.value
            is Data.Error -> return result
        }

        return when {
            response.isSuccessful -> processSuccessfulResponse(response, parseDto)
            else -> processUnsuccessfulResponse(response)
        }
    }

    @Suppress("TooGenericExceptionCaught") // Note: Suppress Detekt. We wanna handle all errors here.
    private suspend fun <DTO> executeRequest(callApi: suspend () -> Response<DTO>): ResultData<Response<DTO>> =
        try {
            Data.Success(withContext(Dispatchers.IO) { callApi() })
        } catch (e: SocketTimeoutException) {
            Data.Error(
                cause = NetworkErrorException("Unable to proceed request.", e),
                type = Data.Error.Type.Timeout
            )
        } catch (e: UnknownHostException) {
            Data.Error(
                cause = NetworkErrorException("Unable to proceed request.", e),
                type = Data.Error.Type.MissingInternet
            )
        } catch (e: ConnectException) {
            Data.Error(
                cause = NetworkErrorException("Unable to proceed request.", e),
                type = Data.Error.Type.MissingInternet
            )
        } catch (e: Exception) {
            Data.Error(
                cause = NetworkErrorException("Unable to proceed request.", e),
                type = Data.Error.Type.General
            )
        }

    private suspend fun <DTO, MODEL> processSuccessfulResponse(
        response: Response<DTO>,
        parseDto: suspend DTO.() -> MODEL
    ): ResultData<MODEL> =
        withContext(Dispatchers.IO) {
            response.body()?.let { responseBody ->
                @Suppress("TooGenericExceptionCaught") // Note: Suppress Detekt. We wanna handle all errors here.
                try {
                    responseBody.parseDto().let { model ->
                        Data.Success(model)
                    }
                } catch (e: Exception) {
                    Data.Error(
                        cause = ResponseParseException(
                            "Unable to parse response body of API request ${response.raw().request.method} " +
                                    "${response.raw().request.url} to model class.",
                            e
                        ),
                        type = Data.Error.Type.General
                    )
                }
            } ?: Data.Error(
                cause = NoResponseBodyException(
                    "API response of ${response.raw().request.method} " +
                            "${response.raw().request.url} doesn't contain any body"
                ),
                type = Data.Error.Type.General
            )
        }

    private fun <DTO, MODEL> processUnsuccessfulResponse(response: Response<DTO>): ResultData<MODEL> =
        Data.Error(
            cause = UnsuccessfulResponseException(
                response.code(),
                "API request ${response.raw().request.method} " +
                        "${response.raw().request.url} finished with unsuccessful http status code ${response.code()}"
            ),
            type = Data.Error.Type.General
        )
}