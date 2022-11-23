package com.nous.example.presentation

import com.nous.example.common.AbstractViewModel

class MainViewModel : AbstractViewModel<MainViewModel.MainState>(MainState()) {
    data class MainState(
        private val someData: String = ""
    ) : State
}