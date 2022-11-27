package com.nous.example.presentation

import com.nous.example.common.AbstractViewModel
import com.nous.example.domain.model.ForwardNavigationEvent
import com.nous.example.domain.model.NavigationEvent
import com.nous.example.domain.model.Route
import com.nous.example.domain.model.ThemeType

class MainViewModel : AbstractViewModel<MainViewModel.State>(State()) {

    fun onNavigationEventConsumed() {}
    fun onWarningDialogDismiss() {}
    fun onOverlayErrorDismiss() {}

    data class State(
        val navigationEvent: NavigationEvent? = ForwardNavigationEvent(Route.Initial),
        val themeType: ThemeType = ThemeType.Automatic,
        val warningDataState: WarningDataState? = null,
        val overlayErrorState: OverlayErrorState? = null,
        val loadingState: LoadingState = LoadingState()
    ) : AbstractViewModel.State {

        data class WarningDataState(
            val title: String,
            val description: String,
            val onDialogDismiss: () -> Unit
        )

        data class OverlayErrorState(
            val error: AbstractViewModel.State.Error,
            val onErrorDismiss: () -> Unit
        )

        data class LoadingState(val message: String? = null, val isVisible: Boolean = false)

        companion object {
            fun isInitialSelected(route: Route?) = route == Route.Initial
        }
    }
}