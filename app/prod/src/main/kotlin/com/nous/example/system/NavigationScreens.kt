package com.nous.example.system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nous.example.domain.model.BackNavigationEvent
import com.nous.example.domain.model.ForwardNavigationEvent
import com.nous.example.domain.model.PopUpNavigationEvent
import com.nous.example.domain.model.Route.Companion.Initial
import com.nous.example.presentation.MainViewModel

@Composable
fun Screens(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Initial(),
        modifier = modifier
    ) {
//        composable(Home()) { HomeScreen() }
    }
}

@Composable
fun NavigationEffect(
    navController: NavHostController,
    viewModel: MainViewModel,
    onNavigationEventConsumed: () -> Unit
) {
    val state = viewModel.states.collectAsState()
    val navigationEvent = state.value.navigationEvent

    SideEffect {
        when (navigationEvent) {
            is BackNavigationEvent -> {
                navController.navigateUp()
                onNavigationEventConsumed()
            }
            is PopUpNavigationEvent -> {
                if (navController.currentDestination?.route != navigationEvent.route()) {
                    navController.popBackStack(navigationEvent.route(), navigationEvent.inclusive)
                }
                onNavigationEventConsumed()
            }
            is ForwardNavigationEvent -> {
                if (navController.currentDestination?.route != navigationEvent.route()) {
                    var navOptions = prepareNavOptions(navigationEvent)

                    navigationEvent.backStackRoutes?.forEach {
                        navController.navigate(it(), navOptions)
                        navOptions = null
                    }

                    if (navigationEvent.popUpToRoute &&
                        navController.backQueue.any { backStackEntry -> backStackEntry.destination.route == navigationEvent.route() }
                    ) {
                        // Note: Popup to route if it's in backstack
                        navController.popBackStack(navigationEvent.route(), false)
                    } else {
                        navController.navigate(navigationEvent.route(), navOptions)
                    }

                    onNavigationEventConsumed()
                }
            }
            null -> Unit
        }
    }
}

private fun prepareNavOptions(navigationEvent: ForwardNavigationEvent): NavOptions? {
    return if (navigationEvent.clearBackStack) {
        NavOptions.Builder().also { navOptionsBuilder ->
            navigationEvent.clearBackStackRoute?.let {
                navOptionsBuilder.setPopUpTo(it(), false)
            } ?: navOptionsBuilder.setPopUpTo(0, false)
        }.build()
    } else {
        null
    }
}