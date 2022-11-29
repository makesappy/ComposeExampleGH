package com.nous.example.data.api

import com.nous.example.domain.model.Data
import com.nous.example.domain.model.LoadableData
import com.nous.example.domain.model.ResultData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

class Api {
    /**
     * Returns [Data.Success] with [T] when every is fine.
     * Returns [Data.Error] with [NetworkErrorException] when request was not proceed due to network error (eq. offline mode)
     * Returns [Data.Error] with [NoResponseBodyException] when request response doesn't contain any body (eq. body is null)
     */
    private suspend fun <T> requestSynchronous(
        callApi: suspend () -> Response<T>
    ): ResultData<T> {
        val response = when (val result = executeRequest(callApi)) {
            is Data.Success -> result.value
            is Data.Error -> return result
        }

        return when {
            response.isSuccessful -> processSuccessfulResponse(response)
            else -> processUnsuccessfulResponse(response)
        }
    }

    /**
     * Emits [Data.Loading] on start.
     * Emits [Data.Success] with [T] when every is fine.
     * Emits [Data.Error] with [NetworkErrorException] when request was not proceed due to network error (eq. offline mode)
     * Emits [Data.Error] with [NoResponseBodyException] when request response doesn't contain any body (eq. body is null)
     *
     * Note: This method uses [requestSynchronous].
     */
    fun <T> request(
        callApi: suspend () -> Response<T>, mapError: Data.Error.() -> Data.Error = { this }
    ): Flow<LoadableData<T>> = flow {
        emit(Data.Loading)

        when (val result = requestSynchronous(callApi)) {
            is Data.Success -> emit(result)
            is Data.Error -> emit(result.mapError())
        }
    }

    private suspend fun <T> executeRequest(callApi: suspend () -> Response<T>): ResultData<Response<T>> =
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
        } catch (e: Exception) {
            Data.Error(
                cause = NetworkErrorException("Unable to proceed request.", e),
                type = Data.Error.Type.General
            )
        }

    private suspend fun <T> processSuccessfulResponse(
        response: Response<T>,
    ): ResultData<T> = withContext(Dispatchers.IO) {
        try {
            Data.Success(requireNotNull(response.body()))
        } catch (e: IllegalArgumentException) {
            Data.Error(cause = e)
        }
    }

    private fun <T> processUnsuccessfulResponse(response: Response<T>): ResultData<T> = Data.Error(
        cause = UnsuccessfulResponseException(
            response.code(),
            "API request ${response.raw().request.method} " + "${response.raw().request.url} finished with unsuccessful http status code ${response.code()}"
        ), type = Data.Error.Type.General
    )
}