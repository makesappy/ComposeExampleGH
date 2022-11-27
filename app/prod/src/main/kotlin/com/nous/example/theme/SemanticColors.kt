package com.nous.example.theme

import androidx.compose.runtime.*
import androidx.compose.ui.graphics.Color

@Stable
class CustomSemanticColors(
    primary: Color,
    secondary: Color,
    tertiary: Color,
    quaternary: Color,
    quinary: Color,
    textPrimary: Color,
    textSecondary: Color,
    textTertiary: Color,
    textQuaternary: Color,
    backgroundPrimary: Color,
    backgroundSecondary: Color,
    backgroundTertiary: Color,
    surfacePrimary: Color,
    surfaceSecondary: Color,
    alert: Color,
    warning: Color,
    positive: Color,
    info: Color,
    componentBackgroundPrimary: Color,
    componentBackgroundSecondary: Color,
    componentBackgroundTertiary: Color,
    componentTextPrimary: Color,
    componentTextSecondary: Color,
    neutralPrimary: Color,
    neutralSecondary: Color,
    neutralTertiary: Color,
    neutralQuaternary: Color,
    neutralQuinary: Color,
) {
    var primary by mutableStateOf(primary, structuralEqualityPolicy())
        internal set
    var secondary by mutableStateOf(secondary, structuralEqualityPolicy())
        internal set
    var tertiary by mutableStateOf(tertiary, structuralEqualityPolicy())
        internal set
    var quaternary by mutableStateOf(quaternary, structuralEqualityPolicy())
        internal set
    var quinary by mutableStateOf(quinary, structuralEqualityPolicy())
        internal set
    var textPrimary by mutableStateOf(textPrimary, structuralEqualityPolicy())
        internal set
    var textSecondary by mutableStateOf(textSecondary, structuralEqualityPolicy())
        internal set
    var textTertiary by mutableStateOf(textTertiary, structuralEqualityPolicy())
        internal set
    var textQuaternary by mutableStateOf(textQuaternary, structuralEqualityPolicy())
        internal set
    var backgroundPrimary by mutableStateOf(backgroundPrimary, structuralEqualityPolicy())
        internal set
    var backgroundSecondary by mutableStateOf(backgroundSecondary, structuralEqualityPolicy())
        internal set
    var backgroundTertiary by mutableStateOf(backgroundTertiary, structuralEqualityPolicy())
        internal set
    var surfacePrimary by mutableStateOf(surfacePrimary, structuralEqualityPolicy())
        internal set
    var surfaceSecondary by mutableStateOf(surfaceSecondary, structuralEqualityPolicy())
        internal set
    var alert by mutableStateOf(alert, structuralEqualityPolicy())
        internal set
    var warning by mutableStateOf(warning, structuralEqualityPolicy())
        internal set
    var positive by mutableStateOf(positive, structuralEqualityPolicy())
        internal set
    var info by mutableStateOf(info, structuralEqualityPolicy())
        internal set
    var componentBackgroundPrimary by mutableStateOf(
        componentBackgroundPrimary,
        structuralEqualityPolicy()
    )
        internal set
    var componentBackgroundSecondary by mutableStateOf(
        componentBackgroundSecondary,
        structuralEqualityPolicy()
    )
        internal set
    var componentBackgroundTertiary by mutableStateOf(
        componentBackgroundTertiary,
        structuralEqualityPolicy()
    )
        internal set
    var componentTextPrimary by mutableStateOf(componentTextPrimary, structuralEqualityPolicy())
        internal set
    var componentTextSecondary by mutableStateOf(componentTextSecondary, structuralEqualityPolicy())
        internal set
    var neutralPrimary by mutableStateOf(neutralPrimary, structuralEqualityPolicy())
        internal set
    var neutralSecondary by mutableStateOf(neutralSecondary, structuralEqualityPolicy())
        internal set
    var neutralTertiary by mutableStateOf(neutralTertiary, structuralEqualityPolicy())
        internal set
    var neutralQuaternary by mutableStateOf(neutralQuaternary, structuralEqualityPolicy())
        internal set
    var neutralQuinary by mutableStateOf(neutralQuinary, structuralEqualityPolicy())
        internal set

    internal fun copy(
        primary2: Color = this.primary,
        secondary2: Color = this.secondary,
        tertiary2: Color = this.tertiary,
        quaternary2: Color = this.quaternary,
        quinary2: Color = this.quinary,
        textPrimary2: Color = this.textPrimary,
        textSecondary2: Color = this.textSecondary,
        textTertiary2: Color = this.textTertiary,
        textQuaternary2: Color = this.textQuaternary,
        backgroundPrimary2: Color = this.backgroundPrimary,
        backgroundSecondary2: Color = this.backgroundSecondary,
        backgroundTertiary2: Color = this.backgroundTertiary,
        surfacePrimary2: Color = this.surfacePrimary,
        surfaceSecondary2: Color = this.surfaceSecondary,
        alert2: Color = this.alert,
        warning2: Color = this.warning,
        positive2: Color = this.positive,
        info2: Color = this.info,
        componentBackgroundPrimary2: Color = this.componentBackgroundPrimary,
        componentBackgroundSecondary2: Color = this.componentBackgroundSecondary,
        componentBackgroundTertiary2: Color = this.componentBackgroundTertiary,
        componentTextPrimary2: Color = this.componentTextPrimary,
        componentTextSecondary2: Color = this.componentTextSecondary,
        neutralPrimary2: Color = this.neutralPrimary,
        neutralSecondary2: Color = this.neutralSecondary,
        neutralTertiary2: Color = this.neutralTertiary,
        neutralQuaternary2: Color = this.neutralQuaternary,
        neutralQuinary2: Color = this.neutralQuinary,
    ) = CustomSemanticColors(
        primary = primary2,
        secondary = secondary2,
        tertiary = tertiary2,
        quaternary = quaternary2,
        quinary = quinary2,
        textPrimary = textPrimary2,
        textSecondary = textSecondary2,
        textTertiary = textTertiary2,
        textQuaternary = textQuaternary2,
        backgroundPrimary = backgroundPrimary2,
        backgroundSecondary = backgroundSecondary2,
        backgroundTertiary = backgroundTertiary2,
        surfacePrimary = surfacePrimary2,
        surfaceSecondary = surfaceSecondary2,
        alert = alert2,
        warning = warning2,
        positive = positive2,
        info = info2,
        componentBackgroundPrimary = componentBackgroundPrimary2,
        componentBackgroundSecondary = componentBackgroundSecondary2,
        componentBackgroundTertiary = componentBackgroundTertiary2,
        componentTextPrimary = componentTextPrimary2,
        componentTextSecondary = componentTextSecondary2,
        neutralPrimary = neutralPrimary2,
        neutralSecondary = neutralSecondary2,
        neutralTertiary = neutralTertiary2,
        neutralQuaternary = neutralQuaternary2,
        neutralQuinary = neutralQuinary2,
    )

    override fun toString(): String = "CustomSemanticColors"
}

internal fun customLightColors() = CustomSemanticColors(
    primary = Color(0xFF1A6783),
    secondary = Color(0xFF7DFFFF),
    tertiary = Color(0xFFF4FBFB),
    quaternary = Color(0xFF193B5C),
    quinary = Color(0xFF569DB7),
    textPrimary = Color(0xFF161718),
    textSecondary = Color(0xFF464748),
    textTertiary = Color(0xFF7C7D7E),
    textQuaternary = Color(0xFF9E9FA0),
    backgroundPrimary = Color(0xFFFFFFFF),
    backgroundSecondary = Color(0xFFF1F1F1),
    backgroundTertiary = Color(0xFFE4E4E4),
    surfacePrimary = Color(0x0F161718),
    surfaceSecondary = Color(0x1F1A1B1C),
    alert = Color(0xFFE82B37),
    warning = Color(0xFFEC6513),
    positive = Color(0xFF37D002),
    info = Color(0xFF2D71D7),
    componentBackgroundPrimary = Color(0xFFE8FFF1),
    componentBackgroundSecondary = Color(0xFFFFFFFF),
    componentBackgroundTertiary = Color(0xFFF1F1F1),
    componentTextPrimary = Color(0xFF216AB3),
    componentTextSecondary = Color(0xFF161718),
    neutralPrimary = Color(0xFFF1F1F1),
    neutralSecondary = Color(0xFFC4C6C7),
    neutralTertiary = Color(0xFF8E8F90),
    neutralQuaternary = Color(0xFF464748),
    neutralQuinary = Color(0xFF232425),
)

internal fun customDarkColors() = CustomSemanticColors(
    primary = Color(0xFF569DB7),
    secondary = Color(0xFF193B5C),
    tertiary = Color(0xFFF4FBFB),
    quaternary = Color(0xFF7DFFFF),
    quinary = Color(0xFF1A6783),
    textPrimary = Color(0xFF161718),
    textSecondary = Color(0xFF464748),
    textTertiary = Color(0xFF7C7D7E),
    textQuaternary = Color(0xFF9E9FA0),
    backgroundPrimary = Color(0xFF0E1124),
    backgroundSecondary = Color(0xFF12193F),
    backgroundTertiary = Color(0xFF151B42),
    surfacePrimary = Color(0x0F161718),
    surfaceSecondary = Color(0x1F1A1B1C),
    alert = Color(0xFFE82B37),
    warning = Color(0xFFEC6513),
    positive = Color(0xFF37D002),
    info = Color(0xFF2D71D7),
    componentBackgroundPrimary = Color(0xFFE8FFF1),
    componentBackgroundSecondary = Color(0xFFFFFFFF),
    componentBackgroundTertiary = Color(0xFFF1F1F1),
    componentTextPrimary = Color(0xFF216AB3),
    componentTextSecondary = Color(0xFF161718),
    neutralPrimary = Color(0xFFF1F1F1),
    neutralSecondary = Color(0xFFC4C6C7),
    neutralTertiary = Color(0xFF8E8F90),
    neutralQuaternary = Color(0xFF464748),
    neutralQuinary = Color(0xFF232425),
)

internal val LocalCustomColors = staticCompositionLocalOf { customLightColors() }