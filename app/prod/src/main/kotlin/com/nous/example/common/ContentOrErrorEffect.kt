package com.nous.example.common

import androidx.compose.runtime.Composable
import com.nous.example.components.GeneralErrorView
import com.nous.example.components.MissingInternetErrorView
import com.nous.example.components.TimeoutErrorView

@Composable
fun ContentOrErrorEffect(
    error: AbstractViewModel.State.Error?,
    onErrorPrimaryButtonClick: (AbstractViewModel.State.Error.Type) -> Unit,
    content: @Composable () -> Unit = {}
) {
    if (error != null) {
        error.Render(onErrorPrimaryButtonClick)
    } else {
        content()
    }
}

@Composable
fun AbstractViewModel.State.Error.Render(
    onErrorPrimaryButtonClick: (AbstractViewModel.State.Error.Type) -> Unit,
) {
    when (type) {
        AbstractViewModel.State.Error.Type.General -> GeneralErrorView(
            onButtonClick = { onErrorPrimaryButtonClick(AbstractViewModel.State.Error.Type.General) }
        )
        AbstractViewModel.State.Error.Type.MissingInternet -> MissingInternetErrorView(
            onButtonClick = { onErrorPrimaryButtonClick(AbstractViewModel.State.Error.Type.MissingInternet) }
        )
        AbstractViewModel.State.Error.Type.Timeout -> TimeoutErrorView(
            onButtonClick = { onErrorPrimaryButtonClick(AbstractViewModel.State.Error.Type.Timeout) }
        )
    }
}