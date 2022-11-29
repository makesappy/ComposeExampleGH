package com.nous.example.data.api

/**
 * Exception indicating that network error. That means request was not proceed due to network problem (fe. no internet connection)
 * @property message Exception message.
 * @property cause Cause exception. Default is null.
 */
class NetworkErrorException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)