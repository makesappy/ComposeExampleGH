package com.nous.example.components

import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.compositeOver
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.nous.example.prod.R
import com.nous.example.theme.CustomTheme

private data class LoadingImageState(
    val isLoading: Boolean
)

@Composable
internal fun LoadingAsyncImage(
    url: String?,
    modifier: Modifier,
) {
    val state = remember {
        mutableStateOf(
            LoadingImageState(
                isLoading = false
            )
        )
    }
    if (url == null) {
        Image(
            painter = painterResource(id = R.drawable.placeholder),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = modifier
                .clip(CircleShape)
        )
    } else {
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(data = url)
                .apply(block = fun ImageRequest.Builder.() {
                    transformations(
                        CircleCropTransformation()
                    )
                }).build(),
            contentDescription = null,
            contentScale = ContentScale.Fit,
            alignment = Alignment.Center,
            onLoading = {
                state.value = state.value.copy(isLoading = true)
            },
            onSuccess = {
                state.value = state.value.copy(isLoading = false)
            },
            onError = {
                state.value = state.value.copy(isLoading = false)
            },
            modifier = modifier.loading(
                isLoading = state.value.isLoading,
                shape = CircleShape
            )
        )
    }
}

fun Modifier.loading(
    isLoading: Boolean,
    shape: Shape = RoundedCornerShape(4.dp)
): Modifier = composed {
    val highlightColor = CustomTheme.colors.textSecondary
        .compositeOver(CustomTheme.colors.textSecondary)

    val highlight = remember {
        PlaceholderHighlight.fade(
            highlightColor = highlightColor,
            animationSpec = infiniteRepeatable(
                animation = tween(
                    delayMillis = ANIMATION_DELAY_MILLIS,
                    durationMillis = ANIMATION_DURATION_MILLIS
                ),
                repeatMode = RepeatMode.Reverse,
            )
        )
    }

    placeholder(
        isLoading,
        highlight = highlight,
        color = CustomTheme.colors.backgroundSecondary,
        shape = shape
    )
}

private const val ANIMATION_DURATION_MILLIS = 800
private const val ANIMATION_DELAY_MILLIS = 200