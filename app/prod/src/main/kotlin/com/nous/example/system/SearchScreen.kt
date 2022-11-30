package com.nous.example.system

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nous.example.components.CustomText
import com.nous.example.components.CustomTopAppBar
import com.nous.example.components.LoadingAsyncImage
import com.nous.example.domain.model.Character
import com.nous.example.domain.model.Classification
import com.nous.example.domain.model.Gender
import com.nous.example.domain.model.Key
import com.nous.example.theme.CustomTheme

private const val rowHeight = 80

@Composable
internal fun <T : Key> SearchScreen(
    textFieldState: MutableState<TextFieldValue>,
    title: String,
    onBackClicked: () -> Unit,
    onRowClicked: (String) -> Unit,
    imageUrl: ((T) -> String?)? = null,
    name: (T) -> String,
    items: List<T>,
    showImage: Boolean = true
) {
    Scaffold(
        topBar = { CustomTopAppBar(title = title, onBackClicked) },
        containerColor = CustomTheme.colors.secondary
    ) {
        Column(
            modifier = Modifier.padding(top = it.calculateTopPadding()),
        ) {
            SearchView(state = textFieldState)
            LazyColumn {
                items(items, key = { k ->
                    k.key
                }) { type ->
                    Box(modifier = Modifier.clickable { onRowClicked(name(type)) }) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            modifier = Modifier.height(rowHeight.dp)
                        ) {
                            if (showImage) {
                                LoadingAsyncImage(
                                    url = imageUrl?.invoke(type),
                                    modifier = Modifier
                                        .width(rowHeight.dp)
                                        .padding(12.dp)
                                )
                            }
                            CustomText(
                                text = name(type),
                                style = CustomTheme.typography.body1,
                                modifier = Modifier
                                    .wrapContentSize()
                                    .align(Alignment.CenterVertically)
                                    .padding(start = if (!showImage) CustomTheme.dimensions.spaceL else 0.dp)
                            )
                        }
                        Divider()
                    }
                }
            }
        }
    }
}

@Composable
private fun SearchView(
    state: MutableState<TextFieldValue>
) {
    TextField(
        value = state.value,
        onValueChange = { value ->
            state.value = value
        },
        modifier = Modifier.fillMaxWidth(),
        textStyle = CustomTheme.typography.body1,
        leadingIcon = {
            Icon(
                Icons.Default.Search, contentDescription = null, modifier = Modifier
                    .padding(
                        start = CustomTheme.dimensions.spaceL,
                        end = CustomTheme.dimensions.spaceS,
                        top = CustomTheme.dimensions.spaceXS,
                        bottom = CustomTheme.dimensions.spaceXS,
                    )
                    .size(CustomTheme.dimensions.spaceM)
            )
        },
        trailingIcon = {
            if (state.value != TextFieldValue("")) {
                IconButton(
                    onClick = {
                        state.value = TextFieldValue("")
                    },
                ) {
                    Icon(
                        Icons.Default.Close,
                        contentDescription = null,
                        modifier = Modifier.size(CustomTheme.dimensions.spaceM)
                    )
                }
            }
        },
        singleLine = true,
        shape = RectangleShape,
        colors = TextFieldDefaults.textFieldColors(
            textColor = CustomTheme.colors.textPrimary,
            disabledTextColor = CustomTheme.colors.textPrimary,
            containerColor = CustomTheme.colors.secondary,
            cursorColor = CustomTheme.colors.textPrimary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            focusedLeadingIconColor = CustomTheme.colors.primary,
            disabledLeadingIconColor = CustomTheme.colors.textSecondary,
            focusedTrailingIconColor = CustomTheme.colors.textPrimary,
            disabledTrailingIconColor = CustomTheme.colors.textSecondary,
            placeholderColor = CustomTheme.colors.textSecondary,
            disabledPlaceholderColor = CustomTheme.colors.textSecondary
        )
    )
}

@Preview
@Composable
private fun Preview() {
    SearchScreen(mutableStateOf(TextFieldValue("")), "Title", {}, {}, { "" }, { "" }, listOf(
        Character(
            name = "Harry Potter",
            gender = Gender.Male,
            classification = Classification.Student,
            imageUrl = "https://hp-api.herokuapp.com/images/harry.jpg",
            isAlive = true,
            isWizard = true
        ), Character(
            name = "Hermione Granger",
            gender = Gender.Female,
            classification = Classification.Student,
            imageUrl = "https://hp-api.herokuapp.com/images/hermione.jpg",
            isAlive = true,
            isWizard = true
        ), Character(
            name = "Ron Weasley",
            gender = Gender.Male,
            classification = Classification.Student,
            imageUrl = "https://hp-api.herokuapp.com/images/ron.jpg",
            isAlive = true,
            isWizard = true
        )
    )
    )
}