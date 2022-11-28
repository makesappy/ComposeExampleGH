package com.nous.example.domain.usecase

import com.nous.example.domain.controller.OverlayErrorController
import com.nous.example.domain.model.Data
import kotlinx.coroutines.flow.Flow

class ObserveOverlayErrorUseCase internal constructor(
    private val controller: OverlayErrorController
) : SynchronousUseCase<Unit, Flow<Data.Error>> {

    override fun invoke(input: Unit): Flow<Data.Error> = controller.overlayError
}