package com.nous.example.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.nous.example.theme.CustomTheme

@Composable
internal fun CustomTopAppBar(
    title: String,
    onBackClicked: () -> Unit,
    contentColor: Color = CustomTheme.colors.backgroundPrimary,
    iconColor: Color = CustomTheme.colors.primary
) {
    TopAppBar(
        title = {
            CustomText(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = CustomTheme.dimensions.spaceXXL),
                text = title.uppercase(),
                style = CustomTheme.typography.header4,
                color = iconColor,
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
                    tint = iconColor,
                    contentDescription = null
                )
            }
        },
        colors = TopAppBarDefaults.mediumTopAppBarColors(
            containerColor = contentColor
        ),
    )
}