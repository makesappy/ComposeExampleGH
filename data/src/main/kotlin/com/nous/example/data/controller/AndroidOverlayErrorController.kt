package com.nous.example.data.controller

import com.nous.example.domain.controller.OverlayErrorController
import com.nous.example.domain.model.Data
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull

internal class AndroidOverlayErrorController : OverlayErrorController {
    private val _overlayError = MutableStateFlow<Data.Error?>(null)
    override val overlayError: Flow<Data.Error> = _overlayError.filterNotNull()

    override suspend fun showOverlayError(error: Data.Error) {
        _overlayError.emit(error)
    }
}