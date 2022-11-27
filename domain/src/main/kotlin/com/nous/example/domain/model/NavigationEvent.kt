package com.nous.example.domain.model

typealias ForwardNavigationEvent = NavigationEvent.ForwardEvent
typealias PopUpNavigationEvent = NavigationEvent.PopUpEvent
typealias BackNavigationEvent = NavigationEvent.BackEvent

/**
 * [NavigationEvent] wraps targeting [Route] where to navigate.
 */
sealed interface NavigationEvent {

    /**
     * Event used to navigate forward.
     *
     * @property route Targeting route where to navigate
     * @property clearBackStack Option to clear navigation back stack before navigation to [route]. Default value is false.
     * @property clearBackStackRoute Route to which backstack should be cleared to. Takes effect only when [clearBackStack] is set to true.
     * Default value is null to clear the whole backstack.
     * @property backStackRoutes Additional routes applied to navigation back stack before navigating to targeting [route]
     * @property popUpToRoute If [route] is in back stack, then popup to [route], otherwise navigate to [route]
     */
    data class ForwardEvent(
        val route: Route,
        val clearBackStack: Boolean = false,
        val clearBackStackRoute: Route? = null,
        val backStackRoutes: List<Route>? = null,
        val popUpToRoute: Boolean = false,
    ) : NavigationEvent

    /**
     * Event used to navigate back by several destinations.
     * Pops the backstack until [route] destination is reached.
     *
     * @property route Targeting route where to navigate back.
     * @property inclusive Whether the given route destination should also be popped from backstack.
     */
    data class PopUpEvent(
        val route: Route,
        val inclusive: Boolean = false,
    ) : NavigationEvent

    /**
     * Event used to navigate back by one destination.
     */
    object BackEvent : NavigationEvent
}
