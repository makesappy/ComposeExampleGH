package com.nous.example.system

import android.app.Application
import com.nous.example.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

internal object DiApplicationDelegate {

    fun onCreate(application: Application) {
        startKoin {
            androidContext(application)
            modules(appModules)
        }
    }
}