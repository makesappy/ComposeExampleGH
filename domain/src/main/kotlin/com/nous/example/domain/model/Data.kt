package com.nous.example.domain.model


sealed interface ResultData<out T>

class Data {
    /**
     * Successfully loaded data.
     *
     * @param value The data itself. The type may be nullable.
     */
    data class Success<out T>(val value: T) : ResultData<T> {
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
    ) : ResultData<Nothing> {
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

inline fun <T> ResultData<T>.onError(block: (Data.Error) -> Unit): ResultData<T> {
    if (this is Data.Error) {
        block(this)
    }
    return this
}

inline fun <T> ResultData<T>.onSuccess(block: (T) -> Unit): ResultData<T> {
    if (this is Data.Success) {
        block(value)
    }
    return this
}

fun <T> Result<T>.foldToData() = fold(
    onFailure = { Data.Error(it) },
    onSuccess = { Data.Success(it) }
)