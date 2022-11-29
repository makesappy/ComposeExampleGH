package com.nous.example.system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.nous.example.common.withRegisteredLifecycle
import com.nous.example.presentation.AllCharactersScreenViewModel
import com.nous.example.prod.R
import org.koin.androidx.compose.getViewModel

@Composable
internal fun AllCharactersScreen() {
    val viewModel = getViewModel<AllCharactersScreenViewModel>().withRegisteredLifecycle()
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val states = viewModel.search(textState.value.text)
        .collectAsState(initial = viewModel.states.value.characters)
    SearchCharactersScreen(
        textFieldState = textState,
        title = stringResource(id = R.string.all_characters_category_title),
        onBackClicked = viewModel::navigateBack,
        items = states.value
    )
}