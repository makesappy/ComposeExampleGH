package com.nous.example.domain.controller

import kotlinx.coroutines.flow.StateFlow

interface InitializerController {
    val isInitialized: StateFlow<Boolean>

    fun setInitialized()
}