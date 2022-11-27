package com.nous.example.components

import androidx.annotation.DrawableRes
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nous.example.theme.CustomTheme
import com.nous.example.theme.ProvideRipple

@Composable
fun CustomPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    @DrawableRes iconResId: Int? = null,
    enabled: Boolean = true,
    fullWidth: Boolean = false,
) {
    CustomIconButton(
        text = text,
        onClick = onClick,
        iconResId = iconResId,
        enabled = enabled,
        modifier = modifier,
        colors = primaryColors(),
        border = primaryBorder(enabled),
        fullWidth = fullWidth
    )
}

@Composable
private fun primaryColors() = CustomButtonColors(
    containerColor = CustomTheme.colors.quinary,
    contentColor = CustomTheme.colors.componentTextSecondary,
    disabledContainerColor = CustomTheme.colors.backgroundSecondary,
    disabledContentColor = CustomTheme.colors.textQuaternary
)

@Composable
private fun primaryBorder(enabled: Boolean) = if (enabled)
    null
else BorderStroke(
    width = 1.0.dp,
    color = CustomTheme.colors.surfaceSecondary
)

@Composable
internal fun CustomActionButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    colors: CustomButtonColors = actionColors()
) {
    CustomButton(
        onClick = onClick,
        modifier = modifier,
        text = text,
        enabled = enabled,
        colors = colors,
        border = null
    )
}

@Composable
private fun actionColors() = CustomButtonColors(
    containerColor = Color.Transparent,
    contentColor = CustomTheme.colors.primary,
    disabledContainerColor = Color.Transparent,
    disabledContentColor = CustomTheme.colors.textQuaternary
)

@Composable
private fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier,
    text: String,
    enabled: Boolean,
    border: BorderStroke?,
    colors: CustomButtonColors
) {
    ProvideRipple(CustomTheme.colors.surfaceSecondary) {
        Button(
            onClick = onClick,
            enabled = enabled,
            shape = CircleShape,
            elevation = elevation(),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
            border = border,
            colors = colors.toComposeButtonColors(),
            modifier = modifier.requiredHeight(32.dp)
        ) {
            CustomText(
                text = text,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = colors.contentColor(enabled = enabled),
                style = CustomTheme.typography.header5
            )
        }
    }
}

@Composable
private fun elevation() = ButtonDefaults.buttonElevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp)

@Composable
private fun CustomIconButton(
    onClick: () -> Unit,
    modifier: Modifier,
    text: String?,
    @DrawableRes iconResId: Int?,
    enabled: Boolean,
    border: BorderStroke?,
    colors: CustomButtonColors,
    fullWidth: Boolean = false
) {
    ProvideRipple(CustomTheme.colors.surfaceSecondary) {
        Button(
            onClick = onClick,
            enabled = enabled,
            shape = CircleShape,
            elevation = elevation(),
            contentPadding = if (text != null) PaddingValues(
                horizontal = 24.dp,
                vertical = 10.dp
            ) else PaddingValues(0.dp),
            border = border,
            colors = colors.toComposeButtonColors(),
            modifier = if (text != null) modifier
                .fillMaxWidth(if (fullWidth) 1.0f else 0.6f)
                .requiredHeight(44.dp) else modifier.size(44.dp),
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                iconResId?.let {
                    Icon(
                        painter = painterResource(it),
                        contentDescription = null,
                        modifier = Modifier.requiredHeight(20.dp)
                    )
                }
                text?.let {
                    CustomText(
                        text = it,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                        color = colors.contentColor(enabled = enabled),
                        style = CustomTheme.typography.header4
                    )
                }
            }
        }
    }
}

internal data class CustomButtonColors(
    private val containerColor: Color,
    private val contentColor: Color,
    private val disabledContainerColor: Color,
    private val disabledContentColor: Color,
) {
    @Composable
    fun toComposeButtonColors(): ButtonColors {
        return ButtonDefaults.buttonColors(
            containerColor = containerColor,
            contentColor = contentColor,
            disabledContainerColor = disabledContainerColor,
            disabledContentColor = disabledContentColor
        )
    }

    fun contentColor(enabled: Boolean): Color {
        return if (enabled) contentColor else disabledContentColor
    }
}