package com.nous.example.system

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
import com.google.android.material.color.MaterialColors
import com.nous.example.components.CustomText
import com.nous.example.domain.model.ShowCategory
import com.nous.example.ext.titleResId
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
    openCategory: (ShowCategory) -> Unit,
    state: HomeViewModel.State
) {
    Scaffold(
        containerColor = CustomTheme.colors.secondary
    ) {
        LazyColumn(
            modifier = Modifier.padding(
                horizontal = CustomTheme.dimensions.spaceXS,
                vertical = CustomTheme.dimensions.spaceXS
            )
        ) {
            item {
                CustomText(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(CustomTheme.dimensions.spaceM),
                    text = stringResource(id = R.string.home_title).uppercase(),
                    style = CustomTheme.typography.header1,
                    color = CustomTheme.colors.textPrimary,
                    textAlign = TextAlign.Center
                )
            }
            items(state.categories) { showCategory ->
                Card(
                    modifier = Modifier
                        .padding(CustomTheme.dimensions.spaceXS),
                    shape = RoundedCornerShape(9.dp),
                    elevation = CardDefaults.cardElevation(
                        defaultElevation = CustomTheme.elevations.elevationM
                    ),
                    colors = CardDefaults.cardColors(
                        containerColor = CustomTheme.colors.primary
                    ),
                    onClick = { openCategory(showCategory) }
                ) {
                    Box(
                        contentAlignment = Alignment.Center, modifier = Modifier
                            .fillMaxSize()
                            .defaultMinSize(minHeight = 75.dp)
                            .padding(CustomTheme.dimensions.spaceS)
                    ) {
                        CustomText(
                            text = stringResource(id = showCategory.titleResId()),
                            style = CustomTheme.typography.body1,
                            color = CustomTheme.colors.secondary,
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