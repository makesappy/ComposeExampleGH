package com.nous.example.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nous.example.theme.CustomTheme
import com.nous.example.prod.R

@Composable
internal fun LoadingDialog() {
    Surface(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(4.dp),
        color = CustomTheme.colors.textSecondary,
        contentColor = CustomTheme.colors.textPrimary
    ) {
        Column(
            modifier = Modifier.padding(vertical = CustomTheme.dimensions.spaceM, horizontal = CustomTheme.dimensions.spaceS),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            SmallCircularProgressIndicator()
            Spacer(modifier = Modifier.height(CustomTheme.dimensions.spaceXS))
            CustomText(
                text = stringResource(id = R.string.global_loading),
                style = CustomTheme.typography.header6,
                textAlign = TextAlign.Center
            )
        }
    }
}