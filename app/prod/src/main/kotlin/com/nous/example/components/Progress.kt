package com.nous.example.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nous.example.theme.CustomTheme

@Composable
fun SmallCircularProgressIndicator(
    modifier: Modifier = Modifier
) {
    CircularProgressIndicatorImpl(
        diameter = 24.dp,
        trackWidth = 3.dp,
        modifier = modifier
    )
}

@Composable
private fun CircularProgressIndicatorImpl(
    diameter: Dp,
    trackWidth: Dp,
    modifier: Modifier
) {
    Box(
        modifier = modifier.size(diameter)
    ) {
        val trackColor = trackColor
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            drawCircle(
                radius = (diameter - trackWidth).toPx() / 2f,
                color = trackColor,
                style = Stroke(width = trackWidth.toPx())
            )
        }
        CircularProgressIndicator(
            color = indicatorColor,
            strokeWidth = trackWidth,
            modifier = Modifier.fillMaxSize()
        )
    }
}

private val trackColor
    @Composable get() = CustomTheme.colors.surfacePrimary

private val indicatorColor
    @Composable get() = CustomTheme.colors.primary