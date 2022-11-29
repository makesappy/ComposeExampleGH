package com.nous.example.components

import androidx.compose.foundation.layout.width
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextLayoutResult
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import com.nous.example.theme.CustomTheme

@Composable
internal fun CustomText(
    text: String,
    style: TextStyle,
    modifier: Modifier = Modifier,
    color: Color = CustomTheme.colors.textPrimary,
    fontSize: TextUnit = TextUnit.Unspecified,
    letterSpacing: TextUnit = TextUnit.Unspecified,
    textDecoration: TextDecoration? = null,
    textAlign: TextAlign? = null,
    lineHeight: TextUnit = TextUnit.Unspecified,
    overflow: TextOverflow = TextOverflow.Clip,
    softWrap: Boolean = true,
    maxLines: Int = Int.MAX_VALUE,
    onTextLayout: (TextLayoutResult) -> Unit = {},
) {
    Text(
        text = if (style == CustomTheme.typography.label) text.uppercase() else text,
        modifier = modifier,
        color = color,
        fontSize = fontSize,
        letterSpacing = letterSpacing,
        textDecoration = textDecoration,
        textAlign = textAlign,
        lineHeight = lineHeight,
        overflow = overflow,
        softWrap = softWrap,
        maxLines = maxLines,
        onTextLayout = onTextLayout,
        style = style
    )
}

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Surface(modifier = Modifier.width(200.dp)) {
        CustomText(text = "Text", style = CustomTheme.typography.body1)
    }
}