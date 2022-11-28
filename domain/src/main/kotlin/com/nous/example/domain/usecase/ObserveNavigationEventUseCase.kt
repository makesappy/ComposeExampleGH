package com.nous.example.domain.usecase

import com.nous.example.domain.controller.MainNavigationController
import com.nous.example.domain.model.NavigationEvent
import kotlinx.coroutines.flow.Flow

class ObserveNavigationEventUseCase(
    private val mainNavigationController: MainNavigationController
) : SynchronousUseCase<Unit, Flow<NavigationEvent>> {
    override fun invoke(input: Unit) = mainNavigationController.navigationEvent
}