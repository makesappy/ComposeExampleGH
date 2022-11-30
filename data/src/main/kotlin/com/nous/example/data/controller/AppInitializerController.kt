package com.nous.example.data.controller

import com.nous.example.domain.controller.InitializerController
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class AppInitializerController : InitializerController {

    private val _isInitialized = MutableStateFlow(false)

    override val isInitialized = _isInitialized.asStateFlow()

    override fun setInitialized() {
        _isInitialized.tryEmit(true)
    }
}