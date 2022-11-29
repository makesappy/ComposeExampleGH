package com.nous.example.data.api

/**
 * Exception indicating that API request finished with unsuccessful response code.
 * That means that Http status code is out of range [200..300].
 * @property httpStatusCode Api response Http Status code
 * @property message Exception message.
 */
internal class UnsuccessfulResponseException(val httpStatusCode: Int, message: String) : RuntimeException(message)