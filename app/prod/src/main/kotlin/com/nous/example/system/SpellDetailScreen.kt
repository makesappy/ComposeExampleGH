package com.nous.example.system

import android.graphics.BitmapFactory
import android.os.Build
import android.widget.ImageView
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.systemuicontroller.SystemUiController
import com.google.accompanist.systemuicontroller.rememberSystemUiController
import com.nous.example.common.withRegisteredLifecycle
import com.nous.example.components.CustomText
import com.nous.example.components.CustomTopAppBar
import com.nous.example.domain.ext.decodedAsArgument
import com.nous.example.domain.model.Spell
import com.nous.example.presentation.SpellDetailViewModel
import com.nous.example.prod.R
import com.nous.example.theme.CustomTheme
import com.nous.example.theme.customLightColors
import jp.wasabeef.blurry.Blurry
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
internal fun SpellDetailScreen(nameArg: String) {
    val systemUiController = rememberSystemUiController()
    val darkColor = customLightColors().primary
    val commonSystemUiColor = CustomTheme.colors.secondary
    systemUiController.setSystemBarsColor(darkColor)

    val name = nameArg.decodedAsArgument

    val viewModel = getViewModel<SpellDetailViewModel>(parameters = {
        parametersOf(name)
    }).withRegisteredLifecycle()

    BackHandler { onBackPressed(viewModel, commonSystemUiColor, systemUiController) }

    SpellDetailScreenImpl(
        name = name, onBackClicked = {
            onBackPressed(viewModel, commonSystemUiColor, systemUiController)
        }, spell = viewModel.states.collectAsState().value.spell, darkColor
    )
}

@Composable
private fun SpellDetailScreenImpl(
    name: String, onBackClicked: () -> Unit, spell: Spell?, darkColor: Color
) {
    Scaffold(modifier = Modifier.paint(
        painter = painterResource(id = R.drawable.splash_logo), contentScale = ContentScale.Fit
    ), containerColor = darkColor, topBar = {
        CustomTopAppBar(
            title = name,
            onBackClicked = onBackClicked,
            contentColor = darkColor,
            iconColor = customLightColors().backgroundPrimary
        )
    }) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .background(darkColor)
        ) {
            val (descriptionRef, logoImage) = createRefs()

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                Image(painter = painterResource(id = R.drawable.splash_logo),
                    contentDescription = null,
                    modifier = Modifier
                        .size(288.dp)
                        .constrainAs(logoImage) {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom)
                            start.linkTo(parent.start)
                        }
                        .blur(10.dp))
            } else {
                AndroidView(factory = {
                    val bmp = BitmapFactory.decodeResource(it.resources, R.drawable.splash_logo)
                    val iv = ImageView(it)
                    Blurry.with(it).radius(25).sampling(2).from(bmp).into(iv)
                    iv
                }, modifier = Modifier
                    .size(288.dp)
                    .constrainAs(logoImage) {
                        top.linkTo(parent.top)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                        start.linkTo(parent.start)
                    })
            }

            Box(modifier = Modifier
                .wrapContentSize()
                .constrainAs(descriptionRef) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }) {
                spell?.run {
                    CustomText(
                        modifier = Modifier.padding(
                            top = it.calculateTopPadding() + CustomTheme.dimensions.spaceXXL,
                            start = CustomTheme.dimensions.spaceXXL,
                            end = CustomTheme.dimensions.spaceXXL
                        ),
                        textAlign = TextAlign.Center,
                        text = description.uppercase(),
                        color = customLightColors().backgroundPrimary,
                        style = CustomTheme.typography.body1
                    )
                }
            }
        }
    }
}

private fun onBackPressed(
    viewModel: SpellDetailViewModel, color: Color, controller: SystemUiController
) {
    controller.setSystemBarsColor(color)
    viewModel.onBack()
}

@Preview
@Composable
private fun Preview() {
    SpellDetailScreenImpl(
        "Reparifors",
        {},
        Spell("Reparifors", "Heals magical ailments like poisoning or paralysis"),
        customLightColors().primary
    )
}