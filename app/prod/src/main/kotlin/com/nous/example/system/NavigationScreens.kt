package com.nous.example.system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.nous.example.domain.model.*
import com.nous.example.domain.model.Route.Companion.Initial
import com.nous.example.presentation.MainViewModel

private const val houseNavArg = "houseArg"
private const val nameNavArg = "nameArg"

@Composable
internal fun Screens(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Initial(),
        modifier = modifier
    ) {
        composable(Route.Splash()) { SplashScreen() }
        composable(Route.Home()) { HomeScreen() }
        composable(Route.AllCharacters()) { AllCharactersScreen() }
        composable(Route.Students()) { StudentsScreen() }
        composable(Route.Staff()) { StaffScreen() }
        composable(Route.Houses()) { HousesScreenUseCase() }
        composable(Route.Spells()) { SpellsScreen() }
        composable("${Route.ByHouse()}/{$houseNavArg}") {
            val houseArg = it.arguments?.getString(houseNavArg) ?: return@composable
            ByHouseCharactersScreen(house = House.valueOf(houseArg))
        }
        composable("${Route.Character()}/{$nameNavArg}") {
            val nameArg = it.arguments?.getString(nameNavArg) ?: return@composable
            CharacterDetailScreen(nameArg = nameArg)
        }
        composable("${Route.Spell()}/{$nameNavArg}") {
            val nameArg = it.arguments?.getString(nameNavArg) ?: return@composable
            SpellDetailScreen(nameArg = nameArg)
        }
    }
}

@Composable
internal fun NavigationEffect(
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
            is PopUpTillNavigationEvent -> {
                if (navController.currentDestination?.route != navigationEvent.route()) {
                    navController.popBackStack(navigationEvent.route(), navigationEvent.inclusive)
                }
                onNavigationEventConsumed()
            }
            is ForwardNavigationEvent -> {
                if (navController.currentDestination?.route != navigationEvent.route()) {
                    var navOptions = prepareNavOptions(navigationEvent)
                    val route = if (navigationEvent.arg != null) {
                        "${navigationEvent.route()}/${navigationEvent.arg}"
                    } else {
                        navigationEvent.route()
                    }

                    navigationEvent.backStackRoutes?.forEach {
                        navController.navigate(it(), navOptions)
                        navOptions = null
                    }

                    if (navigationEvent.popUpToRoute &&
                        navController.backQueue.any { backStackEntry -> backStackEntry.destination.route == navigationEvent.route() }
                    ) {
                        // Note: Popup to route if it's in backstack
                        navController.popBackStack(route, false)
                    } else {
                        navController.navigate(route, navOptions)
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