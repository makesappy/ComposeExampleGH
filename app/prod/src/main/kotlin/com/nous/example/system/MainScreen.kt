package com.nous.example.system

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.nous.example.common.ContentOrErrorEffect
import com.nous.example.common.withRegisteredLifecycle
import com.nous.example.presentation.MainViewModel
import com.nous.example.theme.CustomTheme
import org.koin.androidx.compose.getViewModel

@Composable
internal fun MainScreen() {
    val viewModel = getViewModel<MainViewModel>().withRegisteredLifecycle()

    MainScreenImpl(viewModel = viewModel)
}

@Composable
private fun MainScreenImpl(viewModel: MainViewModel) {
    val state = viewModel.states.collectAsState().value

    CustomTheme {
        val navController = rememberNavController()

        NavigationEffect(
            navController = navController,
            viewModel = viewModel,
            onNavigationEventConsumed = viewModel::onNavigationEventConsumed
        )

        ContentOrErrorEffect(
            error = state.error, onErrorPrimaryButtonClick = viewModel::onOverlayErrorDismiss
        ) {
            Screens(
                navController = navController, modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun MainScreenPreview() {
    MainScreen()
}