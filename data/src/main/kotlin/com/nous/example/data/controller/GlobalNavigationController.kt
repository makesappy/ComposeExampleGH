package com.nous.example.data.controller

import com.nous.example.domain.controller.MainNavigationController
import com.nous.example.domain.model.BackNavigationEvent
import com.nous.example.domain.model.ForwardNavigationEvent
import com.nous.example.domain.model.NavigationEvent
import com.nous.example.domain.model.Route
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow

/**
 * Implementation of all navigation controllers, aggregating and implementing all routes in single place.
 *
 * @see Route
 */
internal class GlobalNavigationController : MainNavigationController {

    private val _navigationEvent = MutableSharedFlow<NavigationEvent>(extraBufferCapacity = 1)
    override val navigationEvent = _navigationEvent.asSharedFlow()

    override fun goBack() = goTo(BackNavigationEvent)
    override fun goToInitial() = goTo(ForwardNavigationEvent(Route.Initial, clearBackStack = true))
    
    private fun goTo(route: Route) = goTo(ForwardNavigationEvent(route))

    private fun goTo(navigationEvent: NavigationEvent) {
        _navigationEvent.tryEmit(navigationEvent)
    }
}