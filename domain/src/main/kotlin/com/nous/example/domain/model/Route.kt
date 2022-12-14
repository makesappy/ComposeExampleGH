package com.nous.example.domain.model

/**
 * Route constants to use with Jetpack Compose navigation instead of plain Strings, as plain Strings are not type- nor typo-safe.
 *
 * Use `invoke` operator function to get the String for Jetpack Compose navigation, but use enum values everywhere else.
 */
enum class Route {
    Splash,
    Home,
    AllCharacters,
    Students,
    Staff,
    Houses,
    Character,
    Spell,
    ByHouse,
    Spells;

    operator fun invoke() = name.lowercase()

    companion object {
        val Initial = Splash
    }
}