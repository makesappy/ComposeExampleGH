package com.nous.example.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.SideEffect
import com.google.accompanist.systemuicontroller.rememberSystemUiController

@Composable
internal fun CustomTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    ProvideColorsDimensionsAndElevations(useDarkTheme) {
        ProvideRipple(CustomTheme.colors.textSecondary) {
            ProvideColorAwareTypography {
                InitializeStatusBar()
                content()
            }
        }
    }
}

@Composable
private fun InitializeStatusBar() {
    val systemUiController = rememberSystemUiController()
    val colors = CustomTheme.colors
    SideEffect {
        systemUiController.setSystemBarsColor(colors.backgroundPrimary)
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

internal object CustomTheme {

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
}