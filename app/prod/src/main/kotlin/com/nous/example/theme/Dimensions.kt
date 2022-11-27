package com.nous.example.theme

import androidx.compose.runtime.Immutable
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Immutable
class CustomDimensions(
    /** XXXS spacing between components. */
    val spaceXXXS: Dp = 2.dp,

    /** XXS spacing between components. */
    val spaceXXS: Dp = 4.dp,

    /** XS spacing between components. */
    val spaceXS: Dp = 8.dp,

    /** S spacing between components. */
    val spaceS: Dp = 16.dp,

    /** M spacing between components. */
    val spaceM: Dp = 24.dp,

    /** L spacing between components. */
    val spaceL: Dp = 32.dp,

    /** XL spacing between components. */
    val spaceXL: Dp = 40.dp,

    /** XXL spacing between components. */
    val spaceXXL: Dp = 48.dp,

    /** XXXL spacing between components. */
    val spaceXXXL: Dp = 64.dp,

    /** Default padding for containers such as screens, bottom sheets or dialogs. */
    val paddingContainer: Dp = 16.dp,

    /** Default horizontal padding for list items. */
    val paddingListHorizontal: Dp = 16.dp,

    /** Default vertical padding for list items. */
    val paddingListVertical: Dp = 12.dp,

    /** Minimal size (width as well as height) of any element, that has some touch behavior (e.g. clickable elements) */
    val minimalTouchSize: Dp = 48.dp
) {
    override fun equals(other: Any?): Boolean = other is CustomDimensions
    override fun hashCode(): Int = 2
    override fun toString(): String = "CustomDimensions"
}

internal val LocalCustomDimensions = staticCompositionLocalOf { CustomDimensions() }