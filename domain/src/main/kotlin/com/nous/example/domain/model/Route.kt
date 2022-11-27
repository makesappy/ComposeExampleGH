package com.nous.example.domain.model

/**
 * Route constants to use with Jetpack Compose navigation instead of plain Strings, as plain Strings are not type- nor typo-safe.
 *
 * Use `invoke` operator function to get the String for Jetpack Compose navigation, but use enum values everywhere else.
 */
enum class Route {
    Initializer;

    operator fun invoke() = name.lowercase()

    companion object {
        val Initial = Initializer
    }
}

/**
 * Returns [Route] from given String, or null if the String itself is null or there is not route corresponding to the given String.
 */
fun String?.asRouteOrNull() = Route.values().firstOrNull { route -> route() == this }