package com.nous.example.system

import androidx.annotation.StringRes
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Divider
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nous.example.common.withRegisteredLifecycle
import com.nous.example.components.CustomText
import com.nous.example.components.CustomTopAppBar
import com.nous.example.components.LoadingAsyncImage
import com.nous.example.data.model.dateTimeFormatter
import com.nous.example.domain.ext.decodedAsArgument
import com.nous.example.domain.model.Character
import com.nous.example.domain.model.Classification
import com.nous.example.domain.model.Gender
import com.nous.example.presentation.CharacterDetailScreenViewModel
import com.nous.example.prod.R
import com.nous.example.theme.CustomTheme
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun CharacterDetailScreen(nameArg: String) {
    val name = nameArg.decodedAsArgument
    val viewModel = getViewModel<CharacterDetailScreenViewModel>(parameters = {
        parametersOf(name)
    }).withRegisteredLifecycle()

    CharacterDetailScreenImpl(
        name = name,
        onBackClicked = viewModel::onBack,
        character = viewModel.states.collectAsState().value.character
    )
}

@Composable
private fun CharacterDetailScreenImpl(
    name: String,
    onBackClicked: () -> Unit,
    character: Character?
) {
    Scaffold(
        containerColor = CustomTheme.colors.secondary,
        topBar = {
            CustomTopAppBar(title = name, onBackClicked)
        }) {
        Column(
            modifier = Modifier.verticalScroll(rememberScrollState())
        ) {
            Spacer(modifier = Modifier.padding(top = it.calculateTopPadding() + CustomTheme.dimensions.spaceXXS))
            LoadingAsyncImage(
                url = character?.imageUrl, modifier = Modifier
                    .size(200.dp)
                    .align(Alignment.CenterHorizontally)
            )
            character?.run {
                alternateNames?.let {
                    CustomRow(
                        titleResId = R.string.alternate_names_title,
                        text = it.joinToString { s -> s.uppercase() })
                }
                species?.let {
                    CustomRow(titleResId = R.string.species_title, text = it)
                }
                CustomRow(titleResId = R.string.gender_title, text = gender.name)
                house?.let { CustomRow(titleResId = R.string.house_title, text = it.name) }
                dateOfBirth?.let {
                    CustomRow(
                        titleResId = R.string.dob_title,
                        text = it.format(dateTimeFormatter)
                    )
                }
                CustomRow(titleResId = R.string.wizard_title, text = isWizard.asString())
                ancestry?.let {
                    CustomRow(
                        titleResId = R.string.ancestry_title,
                        text = it.asString
                    )
                }
                eyeColor?.let { CustomRow(titleResId = R.string.eye_color_title, text = it) }
                hairColor?.let { CustomRow(titleResId = R.string.hair_color_title, text = it) }
                wandWood?.let { CustomRow(titleResId = R.string.wand_wood_title, text = it) }
                wandCore?.let { CustomRow(titleResId = R.string.wand_core_title, text = it) }
                wandLength?.let {
                    CustomRow(
                        titleResId = R.string.wand_length_title,
                        text = it.toString()
                    )
                }
                patronus?.let { CustomRow(titleResId = R.string.patronus_title, text = it) }
                CustomRow(titleResId = R.string.classification_title, text = classification.name)
                actor?.let { CustomRow(titleResId = R.string.actor_title, text = it) }
                alternateActors?.let {
                    CustomRow(
                        titleResId = R.string.alternate_actors_title,
                        text = it.joinToString { s -> s.uppercase() })
                }
                CustomRow(titleResId = R.string.alive_title, text = isAlive.asString())
            }
        }
    }
}

@Composable
fun Boolean.asString(): String = if (this) {
    stringResource(id = R.string.yes_title)
} else {
    stringResource(id = R.string.no_title)
}

@Composable
private fun CustomRow(
    @StringRes
    titleResId: Int,
    text: String
) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    horizontal = CustomTheme.dimensions.spaceM,
                    vertical = CustomTheme.dimensions.spaceS
                ),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            CustomText(
                text = stringResource(id = titleResId).uppercase(),
                style = CustomTheme.typography.body1.copy(fontWeight = FontWeight.Bold)
            )
            CustomText(
                text = text.uppercase(),
                style = CustomTheme.typography.body1.copy(textAlign = TextAlign.End)
            )
        }
        Divider()
    }
}

@Preview
@Composable
private fun Preview() {
    CharacterDetailScreenImpl(
        name = "Harry Potter",
        onBackClicked = { },
        character = Character(
            name = "Harry Potter",
            gender = Gender.Male,
            classification = Classification.Student,
            imageUrl = "https://hp-api.herokuapp.com/images/harry.jpg",
            isAlive = true,
            isWizard = true
        )
    )
}