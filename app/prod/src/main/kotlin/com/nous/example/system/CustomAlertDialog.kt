package com.nous.example.system

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.nous.example.components.CustomActionButton
import com.nous.example.components.CustomText
import com.nous.example.theme.CustomTheme

@Composable
fun CustomAlertDialog(
    title: String,
    text: String,
    onDismiss: () -> Unit,
    positiveButtonText: String,
    modifier: Modifier = Modifier,
    onPositiveButtonClick: () -> Unit = onDismiss,
    negativeButtonText: String? = null,
    onNegativeButtonClick: (() -> Unit) = onDismiss,
    dialogProperties: DialogProperties = DialogProperties()
) {
    Dialog(
        onDismissRequest = onDismiss,
        properties = dialogProperties
    ) {
        Surface(
            modifier = modifier,
            shape = RoundedCornerShape(4.dp),
            color = CustomTheme.colors.componentBackgroundTertiary,
            contentColor = CustomTheme.colors.textPrimary
        ) {
            Column(
                verticalArrangement = Arrangement.spacedBy(CustomTheme.dimensions.spaceS),
                modifier = Modifier.padding(vertical = 16.dp)
            ) {
                CustomText(
                    text = title,
                    style = CustomTheme.typography.header3,
                    modifier = Modifier.padding(horizontal = CustomTheme.dimensions.paddingContainer)

                )
                CustomText(
                    text = text,
                    style = CustomTheme.typography.header6,
                    modifier = Modifier.padding(horizontal = CustomTheme.dimensions.paddingContainer)
                )
                Row(
                    horizontalArrangement = Arrangement.spacedBy(
                        CustomTheme.dimensions.spaceXS,
                        Alignment.End
                    ),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    negativeButtonText?.let {
                        CustomActionButton(
                            text = it,
                            onClick = onNegativeButtonClick
                        )
                    }
                    CustomActionButton(
                        text = positiveButtonText,
                        onClick = onPositiveButtonClick
                    )
                }
            }
        }
    }
}