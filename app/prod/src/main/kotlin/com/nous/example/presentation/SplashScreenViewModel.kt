package com.nous.example.presentation

import com.nous.example.common.AbstractViewModel
import com.nous.example.domain.usecase.OpenHomeScreenUseCase
import com.nous.example.domain.usecase.SetInitializedUseCase
import com.nous.example.domain.usecase.invoke
import kotlinx.coroutines.delay

internal class SplashScreenViewModel(
    private val setInitialized: SetInitializedUseCase,
    private val openHomeScreen: OpenHomeScreenUseCase
) : AbstractViewModel<SplashScreenViewModel.State>(State()) {
    fun onInitialized() {
        launchWhenActive {
            setInitialized()
            // give it some time to show splash
            delay(1000)
            openHomeScreen()
            state = state.copy(initialized = true)
        }
    }

    data class State(
        val initialized: Boolean = false
    ) : AbstractViewModel.State
}