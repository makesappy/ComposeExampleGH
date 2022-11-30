package com.nous.example.system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.text.input.TextFieldValue
import com.nous.example.common.withRegisteredLifecycle
import com.nous.example.domain.model.House
import com.nous.example.presentation.ByHouseScreenViewModel
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun ByHouseCharactersScreen(house: House) {
    val viewModel = getViewModel<ByHouseScreenViewModel>(parameters = {
        parametersOf(house)
    }).withRegisteredLifecycle()
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val states = viewModel.search(textState.value.text)
        .collectAsState(initial = viewModel.states.value.data)
    SearchScreen(
        textFieldState = textState,
        title = house.name,
        onBackClicked = viewModel::navigateBack,
        onRowClicked = viewModel::openDetail,
        items = states.value,
        name = { it.name },
        imageUrl = { it.imageUrl }
    )
}