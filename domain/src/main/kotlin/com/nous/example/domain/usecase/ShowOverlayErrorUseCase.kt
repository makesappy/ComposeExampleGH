package com.nous.example.domain.usecase

import com.nous.example.domain.controller.OverlayErrorController
import com.nous.example.domain.model.Data

class ShowOverlayErrorUseCase internal constructor(
    private val controller: OverlayErrorController
) : SuspendUseCase<Data.Error, Unit> {
    override suspend fun invoke(input: Data.Error) {
        controller.showOverlayError(input)
    }
}