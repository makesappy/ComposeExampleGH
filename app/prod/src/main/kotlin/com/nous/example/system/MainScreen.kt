package com.nous.example.system

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.nous.example.common.ContentOrErrorEffect
import com.nous.example.components.LoadingDialog
import com.nous.example.presentation.MainViewModel
import com.nous.example.prod.R
import com.nous.example.theme.CustomTheme
import org.koin.androidx.compose.getViewModel

@Composable
internal fun MainScreen() {
    val viewModel = getViewModel<MainViewModel>()

    MainScreenImpl(
        viewModel = viewModel,
        onNavigationEventConsumed = viewModel::onNavigationEventConsumed,
        onWarningDialogDismiss = viewModel::onWarningDialogDismiss,
        onOverlayErrorDismiss = viewModel::onOverlayErrorDismiss
    )
}

@Composable
private fun MainScreenImpl(
    viewModel: MainViewModel,
    onNavigationEventConsumed: () -> Unit,
    onWarningDialogDismiss: () -> Unit,
    onOverlayErrorDismiss: () -> Unit
) {
    val state = viewModel.states.collectAsState().value

    CustomTheme {
        val navController = rememberNavController()

        NavigationEffect(
            navController = navController,
            viewModel = viewModel,
            onNavigationEventConsumed = onNavigationEventConsumed
        )

        WarningDialogEffect(state = state, onDialogDismiss = onWarningDialogDismiss)

        ContentOrErrorEffect(
            error = state.overlayErrorState?.error,
            onErrorPrimaryButtonClick = { onOverlayErrorDismiss() }) {
            Screens(
                navController = navController,
                modifier = Modifier.fillMaxSize()
            )
        }
        if (state.loadingState.isVisible) {
            LoadingDialog()
        }
    }
}

@Composable
private fun WarningDialogEffect(
    state: MainViewModel.State,
    onDialogDismiss: () -> Unit
) {
    state.warningDataState?.let { warningData ->
        CustomAlertDialog(
            title = warningData.title,
            text = warningData.description,
            onDismiss = onDialogDismiss,
            positiveButtonText = stringResource(id = R.string.global_ok)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    MainScreen()
}