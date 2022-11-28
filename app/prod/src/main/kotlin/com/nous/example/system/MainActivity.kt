package com.nous.example.system

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val splashScreenDelegate: SplashScreenDelegate by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        splashScreenDelegate.onCreate(this)
        WindowCompat.setDecorFitsSystemWindows(window, true)
        setContent {
            MainScreen()
        }
    }
}