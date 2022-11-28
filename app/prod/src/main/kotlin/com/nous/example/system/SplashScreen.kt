package com.nous.example.system

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.nous.example.common.withRegisteredLifecycle
import com.nous.example.components.SmallCircularProgressIndicator
import com.nous.example.presentation.SplashScreenViewModel
import com.nous.example.prod.R
import com.nous.example.theme.CustomTheme
import org.koin.androidx.compose.getViewModel

@Composable
internal fun SplashScreen() {
    // Note: Draw initializer screen as soon as possible
    SplashScreenImpl()
    getViewModel<SplashScreenViewModel>()
        .withRegisteredLifecycle()
        .onInitialized()
}

@Composable
private fun SplashScreenImpl() {
    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .background(CustomTheme.colors.quaternary)
    ) {
        val (logoImage, progressIndicatorBox) = createRefs()

        Image(
            painter = painterResource(id = R.drawable.splash_logo),
            contentDescription = null,
            modifier = Modifier
                .size(288.dp)
                .constrainAs(logoImage) {
                    top.linkTo(parent.top)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    start.linkTo(parent.start)
                }
        )

        Box(
            modifier = Modifier
                .wrapContentSize()
                .constrainAs(progressIndicatorBox) {
                    top.linkTo(logoImage.bottom)
                    end.linkTo(parent.end)
                    start.linkTo(parent.start)
                }
        ) {
            SmallCircularProgressIndicator()
        }
    }
}

@Preview
@Composable
private fun Preview() {
    SplashScreenImpl()
}