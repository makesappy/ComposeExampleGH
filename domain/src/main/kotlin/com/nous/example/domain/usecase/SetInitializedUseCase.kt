package com.nous.example.domain.usecase

import com.nous.example.domain.controller.InitializerController

class SetInitializedUseCase(
    private val initializerController: InitializerController
) : SynchronousUseCase<Unit, Unit> {

    override fun invoke(input: Unit) {
        initializerController.setInitialized()
    }
}