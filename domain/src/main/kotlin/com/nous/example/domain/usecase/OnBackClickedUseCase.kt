package com.nous.example.domain.usecase

import com.nous.example.domain.controller.MainNavigationController

class OnBackClickedUseCase(
    private val mainNavigationController: MainNavigationController
) : SynchronousUseCase<Unit,Unit> {
    override fun invoke(input: Unit) = mainNavigationController.goBack()
}