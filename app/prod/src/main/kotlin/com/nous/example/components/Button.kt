package com.nous.example.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nous.example.theme.CustomTheme
import com.nous.example.theme.ProvideRipple

@Composable
internal fun CustomPrimaryButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    CustomButton(
        text = text,
        onClick = onClick,
        enabled = enabled,
        modifier = modifier,
        colors = primaryColors(),
    )
}

@Composable
private fun primaryColors() = CustomButtonColors(
    containerColor = CustomTheme.colors.primary,
    contentColor = CustomTheme.colors.secondary,
    disabledContainerColor = CustomTheme.colors.backgroundSecondary,
    disabledContentColor = CustomTheme.colors.textSecondary
)

@Composable
private fun CustomButton(
    onClick: () -> Unit,
    modifier: Modifier,
    text: String,
    enabled: Boolean,
    colors: CustomButtonColors
) {
    ProvideRipple(CustomTheme.colors.backgroundSecondary) {
        Button(
            onClick = onClick,
            enabled = enabled,
            shape = CircleShape,
            elevation = ButtonDefaults.buttonElevation(0.dp, 0.dp, 0.dp, 0.dp, 0.dp),
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 6.dp),
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

@Preview
@Composable
private fun Preview() {
    Surface(modifier = Modifier.width(200.dp)) {
        CustomPrimaryButton(text = "Text", onClick = { })
    }
}