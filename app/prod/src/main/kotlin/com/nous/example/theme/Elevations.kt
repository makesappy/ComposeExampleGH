package com.nous.example.theme

import androidx.compose.runtime.*
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Stable
internal class CustomElevations(
    elevationS: Dp,
    elevationM: Dp,
    elevationL: Dp,
    elevationXL: Dp
) {
    /** Small elevation. Effective only in light mode. */
    var elevationS by mutableStateOf(elevationS, structuralEqualityPolicy())
        internal set

    /** Medium elevation. Effective only in light mode. */
    var elevationM by mutableStateOf(elevationM, structuralEqualityPolicy())
        internal set

    /** Large elevation. Effective only in light mode. */
    var elevationL by mutableStateOf(elevationL, structuralEqualityPolicy())
        internal set

    /** Extra large elevation. Effective only in light mode. */
    var elevationXL by mutableStateOf(elevationXL, structuralEqualityPolicy())
        internal set

    internal fun copy(
        elevationS: Dp = this.elevationS,
        elevationM: Dp = this.elevationM,
        elevationL: Dp = this.elevationL,
        elevationXL: Dp = this.elevationXL
    ) = CustomElevations(
        elevationS = elevationS,
        elevationM = elevationM,
        elevationL = elevationL,
        elevationXL = elevationXL
    )

    override fun toString(): String = "CustomElevations"
}


internal fun customLightElevations() = CustomElevations(
    elevationS = 1.dp,
    elevationM = 3.dp,
    elevationL = 4.dp,
    elevationXL = 6.dp
)

internal fun customDarkElevations() = CustomElevations(
    elevationS = 0.dp,
    elevationM = 0.dp,
    elevationL = 0.dp,
    elevationXL = 0.dp
)

internal val LocalCustomElevations = staticCompositionLocalOf { customLightElevations() }