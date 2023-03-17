package com.nous.example.presentation

import com.nous.example.common.AbstractViewModel
import com.nous.example.domain.model.*
import com.nous.example.domain.usecase.ObserveNavigationEventUseCase
import com.nous.example.domain.usecase.ObserveOverlayErrorUseCase
import com.nous.example.domain.usecase.invoke

internal class MainViewModel(
    private val observeNavigationEvents: ObserveNavigationEventUseCase,
    private val observeError: ObserveOverlayErrorUseCase
) : AbstractViewModel<MainViewModel.State>(State()) {

    init {
        launchWhenActive {
            observeNavigationEvents().collect {
                state = state.copy(navigationEvent = it)
            }
        }
        launchWhenActive {
            observeError().collect {
                state = state.copy(error = it)
            }
        }
    }

    fun onNavigationEventConsumed() {
        state = state.copy(navigationEvent = null)
    }

    fun onOverlayErrorDismiss() {
        state = state.copy(error = null)
    }

    data class State(
        val navigationEvent: NavigationEvent? = ForwardNavigationEvent(Route.Initial),
        val error: Data.Error? = null,
    ) : AbstractViewModel.State
}