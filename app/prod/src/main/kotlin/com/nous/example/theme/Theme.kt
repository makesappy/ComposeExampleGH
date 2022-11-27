package com.nous.example.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val DarkColorScheme = darkColorScheme(
    primary = Color(0xff124559),
    secondary = Color(0xff0A1D2F),
    tertiary = Color(0xffF4F9FB),
    background = Color(0xFF0A1D2F),
    surface = Color(0xFF0A1D2F),
    onPrimary = Color(0xFF0A1D2F),
    onSecondary = Color(0xFF0A1D2F),
    onTertiary = Color(0xFF0A1D2F),
    onBackground = Color(0xffF4F9FB),
    onSurface = Color(0xffF4F9FB),
)

private val LightColorScheme = lightColorScheme(
    primary = Color(0xff124559),
    secondary = Color(0xffF4F9FB),
    tertiary = Color(0xff0A1D2F),
    background = Color(0xffF4F9FB),
    surface = Color(0xffF4F9FB),
    onPrimary = Color(0xffF4F9FB),
    onSecondary = Color(0xffF4F9FB),
    onTertiary = Color(0xffF4F9FB),
    onBackground = Color(0xFF0A1D2F),
    onSurface = Color(0xFF0A1D2F),
)

@Composable
fun CustomTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    CustomTheme.isThemeInDarkMode = useDarkTheme

    ProvideColorsDimensionsAndElevations(useDarkTheme) {
        ProvideRipple(CustomTheme.colors.surfacePrimary) {
            ProvideColorAwareTypography {
                InitializeStatusBar(useDarkTheme)
                content()
            }
        }
    }
}

@Composable
private fun InitializeStatusBar(useDarkTheme: Boolean) {
    val systemUiController = rememberSystemUiController()

    SideEffect {
        systemUiController.setStatusBarColor(if (useDarkTheme) Color.Companion.Black else Color.White)
    }
}

@Composable
private fun ProvideColorsDimensionsAndElevations(
    useDarkTheme: Boolean,
    content: @Composable () -> Unit
) {
    val colors = if (useDarkTheme) customDarkColors() else customLightColors()
    val elevations = if (useDarkTheme) customDarkElevations() else customLightElevations()

    CompositionLocalProvider(
        LocalCustomDimensions provides CustomTheme.dimensions,
        LocalCustomElevations provides elevations,
        LocalCustomColors provides colors
    ) {
        content()
    }
}

@Composable
private fun ProvideColorAwareTypography(content: @Composable () -> Unit) {
    val typography = customColorAwareTypography(
        textPrimary = CustomTheme.colors.textPrimary,
        textSecondary = CustomTheme.colors.textSecondary,
    )

    CompositionLocalProvider(
        LocalCustomTypography provides typography
    ) {
        content()
    }
}

object CustomTheme {

    val typography: CustomTypography
        @Composable
        @ReadOnlyComposable
        get() = LocalCustomTypography.current

    val dimensions: CustomDimensions
        @Composable
        @ReadOnlyComposable
        get() = LocalCustomDimensions.current

    val elevations: CustomElevations
        @Composable
        @ReadOnlyComposable
        get() = LocalCustomElevations.current

    val colors: CustomSemanticColors
        @Composable
        @ReadOnlyComposable
        get() = LocalCustomColors.current

    var isThemeInDarkMode: Boolean = false
        internal set
}