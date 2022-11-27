package com.nous.example.system

import android.app.Activity
import android.os.Build
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.nous.example.data.controller.AppInitializerController

internal class SplashScreenDelegate(private val initializerController: AppInitializerController) {

    fun onCreate(activity: Activity) {
        activity.installSplashScreen().apply {
            setKeepOnScreenCondition {
                (!initializerController.isInitialized.value)
            }
        }

        // Note: Override of exit animation removes unwanted flash animation. Unfortunately currently only for API 31+
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            activity.splashScreen.setOnExitAnimationListener {
                it.remove()
            }
        }
    }
}