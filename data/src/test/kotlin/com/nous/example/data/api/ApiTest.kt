package com.nous.example.data.api

import com.nous.example.domain.model.Data
import io.kotest.matchers.shouldBe
import io.kotest.matchers.shouldNotBe
import io.kotest.matchers.types.shouldBeInstanceOf
import io.mockk.every
import io.mockk.mockk
import junitparams.JUnitParamsRunner
import junitparams.Parameters
import kotlinx.coroutines.test.runTest
import okhttp3.HttpUrl
import org.junit.Test
import org.junit.runner.RunWith
import retrofit2.Response
import java.net.ConnectException
import java.net.UnknownHostException

/**
 * Example of unit-test for data layer
 */
@RunWith(JUnitParamsRunner::class)
internal class ApiTest {

    private val anyDto = Any()
    private val anyModel = Any()

    private val anyOkHttpRequest = mockk<okhttp3.Request> {
        every { this@mockk.method } returns "GET"
        every { this@mockk.url } returns HttpUrl.Builder().scheme("https").host("example.cz")
            .build()
    }
    private val anyOkHttpResponse = mockk<okhttp3.Response> {
        every { this@mockk.request } returns anyOkHttpRequest
    }
    private val anyResponse = mockk<Response<Any>> {
        every { this@mockk.isSuccessful } returns true
        every { this@mockk.body() } returns anyDto
        every { this@mockk.code() } returns 200
        every { this@mockk.raw() } returns anyOkHttpResponse
    }
    private val callApi: () -> Response<Any> = { anyResponse }
    private val toModel: (Any) -> Any = { anyModel }

    @Test
    fun `should emit data in proper order while requesting with parsing to model when response is successful`() =
        runTest {
            every { anyResponse.isSuccessful } returns true
            val api = Api()

            api.request(callApi, toModel) shouldBe Data.Success(anyModel)
        }

    @Test
    fun `should emit data in proper order while requesting with parsing to model when response is not successful`() =
        runTest {
            every { anyResponse.isSuccessful } returns false
            every { anyResponse.code() } returns 500

            val api = Api()

            api.request(callApi, toModel) as? Data.Error shouldNotBe null
        }

    @Test
    fun `should finish with NoResponseBodyException error when no body available`() = runTest {
        every { anyResponse.isSuccessful } returns true
        every { anyResponse.body() } returns null

        val api = Api()

        (api.request(
            callApi,
            toModel
        ) as? Data.Error)?.cause as? NoResponseBodyException shouldNotBe null
    }


    @Suppress("unused")// actually used as a test method input argument, see below
    private fun networkErrorExceptionData() = arrayOf(
        arrayOf(UnknownHostException(), Data.Error.Type.MissingInternet),
        arrayOf(RuntimeException(), Data.Error.Type.General),
        arrayOf(ConnectException(), Data.Error.Type.MissingInternet)
    )

    @Test
    @Parameters(method = "networkErrorExceptionData")
    fun `should finish with NetworkErrorException error when call api failed`(
        exception: Exception,
        expectedErrorType: Data.Error.Type
    ) = runTest {
        every { anyResponse.isSuccessful } returns true
        val callApiWithException = { throw exception }

        val api = Api()

        (api.request(callApiWithException, toModel) as? Data.Error).let {
            it shouldNotBe null
            it?.cause?.shouldBeInstanceOf<NetworkErrorException>()
            it?.type shouldBe expectedErrorType
        }
    }

    @Test
    fun `should finish with ResponseParseException error when dto parse to model failed`() =
        runTest {
            every { anyResponse.isSuccessful } returns true
            @Suppress("TooGenericExceptionThrown") // Note: Suppresses Detekt issue
            val toModelWithException: (Any) -> Any = { throw RuntimeException() }

            val api = Api()

            (api.request(callApi, toModelWithException) as? Data.Error).let {
                it shouldNotBe null
                it?.cause?.shouldBeInstanceOf<ResponseParseException>()
                it?.type shouldBe Data.Error.Type.General
            }
        }

    @Test
    fun `should finish with UnsuccessfulResponseException error when response is not successful`() =
        runTest {
            every { anyResponse.isSuccessful } returns false
            every { anyResponse.code() } returns 500

            val api = Api()

            (api.request(callApi, toModel) as? Data.Error).let {
                it shouldNotBe null
                it?.cause?.shouldBeInstanceOf<UnsuccessfulResponseException>()
                it?.type shouldBe Data.Error.Type.General
            }
        }
}