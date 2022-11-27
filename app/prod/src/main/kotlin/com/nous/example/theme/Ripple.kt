package com.nous.example.theme

import androidx.compose.foundation.LocalIndication
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.RippleTheme
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color

@Composable
internal fun ProvideRipple(
    color: Color,
    content: @Composable () -> Unit
) {
    val rippleIndication = rememberRipple(color = color)
    val rippleTheme = remember {
        object : RippleTheme {
            @Composable
            override fun defaultColor(): Color = color

            @Composable
            override fun rippleAlpha(): RippleAlpha {
                val alpha = color.alpha
                return RippleAlpha(alpha, alpha, alpha, alpha)
            }
        }
    }

    CompositionLocalProvider(
        LocalIndication provides rippleIndication,
        LocalRippleTheme provides rippleTheme
    ) {
        content()
    }
}