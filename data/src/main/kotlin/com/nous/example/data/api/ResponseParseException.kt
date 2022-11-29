package com.nous.example.data.api

/**
 * Exception indicating that some error occurred during parsing DTO object to MODEL object.
 * @property message Exception message.
 * @property cause Cause exception. Default is null.
 */
internal class ResponseParseException(message: String, cause: Throwable? = null) : RuntimeException(message, cause)