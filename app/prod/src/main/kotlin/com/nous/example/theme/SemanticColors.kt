package com.nous.example.theme

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Stable
internal class CustomSemanticColors(
    primary: Color,
    secondary: Color,
    textPrimary: Color,
    textSecondary: Color,
    backgroundPrimary: Color,
    backgroundSecondary: Color
) {
    var primary by mutableStateOf(primary, structuralEqualityPolicy())
        internal set
    var secondary by mutableStateOf(secondary, structuralEqualityPolicy())
        internal set
    var textPrimary by mutableStateOf(textPrimary, structuralEqualityPolicy())
        internal set
    var textSecondary by mutableStateOf(textSecondary, structuralEqualityPolicy())
        internal set
    var backgroundPrimary by mutableStateOf(backgroundPrimary, structuralEqualityPolicy())
        internal set
    var backgroundSecondary by mutableStateOf(backgroundSecondary, structuralEqualityPolicy())
        internal set

    internal fun copy(
        primary2: Color = this.primary,
        secondary2: Color = this.secondary,
        textPrimary2: Color = this.textPrimary,
        textSecondary2: Color = this.textSecondary,
        backgroundPrimary2: Color = this.backgroundPrimary,
        backgroundSecondary2: Color = this.backgroundSecondary,
    ) = CustomSemanticColors(
        primary = primary2,
        secondary = secondary2,
        textPrimary = textPrimary2,
        textSecondary = textSecondary2,
        backgroundPrimary = backgroundPrimary2,
        backgroundSecondary = backgroundSecondary2,
    )

    override fun toString(): String = "CustomSemanticColors"
}

internal fun customLightColors() = CustomSemanticColors(
    primary = Color(0xFF1E1E1E),
    secondary = Color(0xFFFFFFFF),
    textPrimary = Color(0xFF1E1E1E),
    textSecondary = Color(0xFF868585),
    backgroundPrimary = Color(0xFFFFFFFF),
    backgroundSecondary = Color(0xFFDADADA),
)

internal fun customDarkColors() = CustomSemanticColors(
    primary = Color(0xFFFFFFFF),
    secondary = Color(0xFF1E1E1E),
    textPrimary = Color(0xFFFFFFFF),
    textSecondary = Color(0xFF868585),
    backgroundPrimary = Color(0xFF1E1E1E),
    backgroundSecondary = Color(0xFFDADADA)
)

internal val LocalCustomColors = staticCompositionLocalOf { customLightColors() }