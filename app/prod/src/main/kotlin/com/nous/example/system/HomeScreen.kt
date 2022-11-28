package com.nous.example.system

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nous.example.components.CustomText
import com.nous.example.domain.model.DataCategory
import com.nous.example.ext.textResId
import com.nous.example.presentation.HomeViewModel
import com.nous.example.prod.R
import com.nous.example.theme.CustomTheme
import org.koin.androidx.compose.getViewModel

@Composable
internal fun HomeScreen() {
    val viewModel = getViewModel<HomeViewModel>()
    viewModel.states.collectAsState()
    HomeScreenImpl(
        openCategory = viewModel::openCategory,
        state = viewModel.states.collectAsState().value
    )
}

@Composable
private fun HomeScreenImpl(
    openCategory: (DataCategory) -> Unit,
    state: HomeViewModel.State
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    CustomText(
                        text = stringResource(id = R.string.home_title),
                        style = CustomTheme.typography.header2.copy(color = CustomTheme.colors.textPrimary)
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = CustomTheme.colors.primary
                ),
            )
        }
    ) {
        LazyVerticalGrid(
            columns = GridCells.Adaptive(minSize = 128.dp),
            modifier = Modifier.padding(
                horizontal = CustomTheme.dimensions.spaceXS,
                vertical = it.calculateTopPadding()
            )
        ) {
            items(state.categories.size) {
                val category = state.categories[it]
                Card(
                    modifier = Modifier
                        .padding(CustomTheme.dimensions.spaceXS),
                    shape = RoundedCornerShape(4.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = CustomTheme.elevations.elevationM
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = CustomTheme.colors.quaternary,
                        contentColor = CustomTheme.colors.textPrimary
                    ),
                    onClick = { openCategory(category) }
                ) {
                    Box(
                        contentAlignment = Alignment.Center, modifier = Modifier
                            .fillMaxSize()
                            .defaultMinSize(minHeight = 160.dp)
                            .padding(CustomTheme.dimensions.spaceXS)
                    ) {
                        CustomText(
                            text = stringResource(id = category.textResId()),
                            style = CustomTheme.typography.body1,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HomeScreenImpl(openCategory = {}, state = HomeViewModel.State())
}