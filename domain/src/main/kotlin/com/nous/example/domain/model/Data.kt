package com.nous.example.domain.model

sealed interface LoadableData<out T>

sealed interface ResultData<out T>

class Data {
    /**
     * Indicates that the data is loading.
     */
    object Loading : LoadableData<Nothing> {
        override fun toString() = "Loading"
    }

    /**
     * Successfully loaded data.
     *
     * @param value The data itself. The type may be nullable.
     */
    data class Success<out T>(val value: T) : LoadableData<T>,  ResultData<T> {
        override fun toString() = "Success($value)"
    }

    /**
     * Data.Error while loading data.
     *
     * @param cause Exception that caused the error.
     * @param previousError Previous error in the chain to which is this error appended
     */
    data class Error(
        val cause: Throwable,
        val previousError: Error? = null,
        val type: Type = Type.General,
    ) : LoadableData<Nothing>, ResultData<Nothing> {
        override fun toString() = "Error ($cause) ${previousError?.let { "-> $it" }.orEmpty()}"

        /**
         * Error type
         * @property priority Error type priority. Higher [priority] value means more prior error type.
         */
        enum class Type(val priority: Int) {
            General(1),
            Timeout(2),
            MissingInternet(3);
        }
    }
}