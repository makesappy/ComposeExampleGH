package com.nous.example.system

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.nous.example.common.withRegisteredLifecycle
import com.nous.example.presentation.StudentsScreenViewModel
import com.nous.example.prod.R
import org.koin.androidx.compose.getViewModel

@Composable
internal fun StudentsScreen() {
    val viewModel = getViewModel<StudentsScreenViewModel>().withRegisteredLifecycle()
    val textState = remember { mutableStateOf(TextFieldValue("")) }
    val states = viewModel.search(textState.value.text)
        .collectAsState(initial = viewModel.states.value.data)
    SearchScreen(
        textFieldState = textState,
        title = stringResource(id = R.string.students_category_title),
        onBackClicked = viewModel::navigateBack,
        onRowClicked = viewModel::openDetail,
        items = states.value,
        name = { it.name },
        imageUrl = { it.imageUrl }
    )
}