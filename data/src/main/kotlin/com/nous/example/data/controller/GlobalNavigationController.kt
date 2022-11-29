package com.nous.example.data.controller

import com.nous.example.domain.controller.MainNavigationController
import com.nous.example.domain.model.*
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
    override fun goToHome() =
        goTo(ForwardNavigationEvent(route = Route.Home, clearBackStack = true))

    override fun goToAllCharacters() = goTo(Route.AllCharacters)
    override fun goToStudents() = goTo(Route.Students)
    override fun goToStaff() = goTo(Route.Staff)
    override fun goToByHouse(house: House) =
        goTo(ForwardNavigationEvent(Route.ByHouse, arg = house.name))

    override fun goToSpells() = goTo(Route.Spells)
    override fun goToHouses() = goTo(Route.Houses)

    private fun goTo(route: Route) = goTo(ForwardNavigationEvent(route))
    private fun goTo(navigationEvent: NavigationEvent) {
        _navigationEvent.tryEmit(navigationEvent)
    }
}