package com.nous.example.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nous.example.prod.BuildConfig
import com.nous.example.prod.R
import com.nous.example.theme.CustomTheme
import java.time.LocalDateTime

@Composable
internal fun ErrorView(
    title: String,
    description: String,
    primaryButtonTitle: String,
    onPrimaryClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(CustomTheme.colors.backgroundPrimary)
            .padding(
                start = CustomTheme.dimensions.spaceM,
                end = CustomTheme.dimensions.spaceM,
                top = CustomTheme.dimensions.spaceXXXL,
                bottom = CustomTheme.dimensions.spaceL
            ),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.Start
        ) {
            CustomText(
                text = title,
                style = CustomTheme.typography.header1
            )
            Spacer(modifier = Modifier.height(CustomTheme.dimensions.spaceM))
            CustomText(
                text = description,
                style = CustomTheme.typography.body1
            )
        }

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CustomText(
                text = stringResource(id = R.string.error_app_version, BuildConfig.VERSION_NAME),
                style = CustomTheme.typography.disclaimer
            )
            CustomText(
                text = LocalDateTime.now().toString(),
                style = CustomTheme.typography.disclaimer
            )
            Spacer(modifier = Modifier.height(CustomTheme.dimensions.spaceXL))
            CustomPrimaryButton(
                modifier = Modifier.defaultMinSize(minWidth = 200.dp),
                text = primaryButtonTitle,
                onClick = onPrimaryClick
            )
        }
    }
}

@Composable
internal fun MissingInternetErrorView(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ErrorView(
        title = stringResource(id = R.string.error_no_internet_header),
        description = stringResource(id = R.string.error_no_internet_body),
        primaryButtonTitle = stringResource(id = R.string.global_ok),
        onPrimaryClick = onButtonClick,
        modifier = modifier
    )
}

@Composable
internal fun TimeoutErrorView(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ErrorView(
        title = stringResource(id = R.string.error_timeout_header),
        description = stringResource(id = R.string.error_timeout_body),
        primaryButtonTitle = stringResource(id = R.string.global_ok),
        onPrimaryClick = onButtonClick,
        modifier = modifier
    )
}

@Composable
internal fun GeneralErrorView(
    onButtonClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    ErrorView(
        title = stringResource(id = R.string.error_general_header),
        description = stringResource(id = R.string.error_general_body),
        primaryButtonTitle = stringResource(id = R.string.global_ok),
        onPrimaryClick = onButtonClick,
        modifier = modifier
    )
}

@Preview
@Composable
private fun Preview() {
    ErrorView(
        title = "My title",
        description = "Error description",
        primaryButtonTitle = "Primary button",
        onPrimaryClick = {}
    )
}