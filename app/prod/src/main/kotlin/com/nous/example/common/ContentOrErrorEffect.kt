package com.nous.example.common

import androidx.compose.runtime.Composable
import com.nous.example.components.GeneralErrorView
import com.nous.example.components.MissingInternetErrorView
import com.nous.example.components.TimeoutErrorView
import com.nous.example.domain.model.Data

@Composable
internal fun ContentOrErrorEffect(
    error: Data.Error?,
    onErrorPrimaryButtonClick: () -> Unit,
    content: @Composable () -> Unit = {}
) {
    if (error != null) {
        error.Render(onErrorPrimaryButtonClick)
    } else {
        content()
    }
}

@Composable
internal fun Data.Error.Render(
    onErrorPrimaryButtonClick: () -> Unit,
) {
    when (type) {
        Data.Error.Type.General -> GeneralErrorView(
            onButtonClick = { onErrorPrimaryButtonClick() }
        )
        Data.Error.Type.MissingInternet -> MissingInternetErrorView(
            onButtonClick = { onErrorPrimaryButtonClick() }
        )
        Data.Error.Type.Timeout -> TimeoutErrorView(
            onButtonClick = { onErrorPrimaryButtonClick() }
        )
    }
}