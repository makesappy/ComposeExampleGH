package com.nous.example.domain.controller

import com.nous.example.domain.model.Data
import kotlinx.coroutines.flow.Flow

interface OverlayErrorController {
    val overlayError: Flow<Data.Error>
    suspend fun showOverlayError(error: Data.Error)
}