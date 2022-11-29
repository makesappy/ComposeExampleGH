package com.nous.example.system

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nous.example.common.withRegisteredLifecycle
import com.nous.example.components.CustomText
import com.nous.example.components.LoadingAsyncImage
import com.nous.example.domain.model.House
import com.nous.example.presentation.HousesViewModel
import com.nous.example.prod.R
import com.nous.example.theme.CustomTheme
import org.koin.androidx.compose.getViewModel

@Composable
internal fun HousesScreenUseCase() {
    val viewModel = getViewModel<HousesViewModel>().withRegisteredLifecycle()
    HousesScreenImpl(
        houses = viewModel.states.collectAsState().value.houses,
        onBackClicked = viewModel::onBack,
        openHouse = viewModel::openHouse
    )
}

@Composable
private fun HousesScreenImpl(
    houses: List<House>,
    onBackClicked: () -> Unit,
    openHouse: (House) -> Unit
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    CustomText(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(end = CustomTheme.dimensions.spaceL),
                        text = stringResource(id = R.string.houses_category_title).uppercase(),
                        style = CustomTheme.typography.header4,
                        textAlign = TextAlign.Center
                    )
                },
                navigationIcon = {
                    IconButton(
                        onClick = onBackClicked,
                        enabled = true
                    ) {
                        Icon(
                            Icons.Default.ArrowBack,
                            tint = CustomTheme.colors.primary,
                            contentDescription = null
                        )
                    }
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(
                    containerColor = CustomTheme.colors.backgroundPrimary
                ),
            )
        },
        containerColor = CustomTheme.colors.secondary
    ) {
        LazyColumn(modifier = Modifier.padding(top = it.calculateTopPadding())) {
            items(houses) { house ->
                Box(modifier = Modifier.clickable {
                    openHouse(house)
                }) {
                    Row(
                        horizontalArrangement = Arrangement.Center
                    ) {
                        LoadingAsyncImage(
                            url = house.imageUrl,
                            modifier = Modifier
                                .size(80.dp)
                                .padding(12.dp)
                        )
                        CustomText(
                            text = house.name,
                            style = CustomTheme.typography.body1,
                            modifier = Modifier
                                .fillMaxHeight()
                                .align(Alignment.CenterVertically)
                        )
                    }
                    Divider()
                }
            }
        }
    }
}

@Preview
@Composable
private fun Preview() {
    HousesScreenImpl(House.values().toList(), {}, {})
}