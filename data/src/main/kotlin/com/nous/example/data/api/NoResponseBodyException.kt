package com.nous.example.data.api

/**
 * Exception indicating that API response doesn't contain any body (eq. body is null)
 * @property message Exception message
 */
internal class NoResponseBodyException(message: String) : RuntimeException(message)