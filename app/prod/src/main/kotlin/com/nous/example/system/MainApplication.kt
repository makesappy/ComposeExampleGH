package com.nous.example.system

import android.app.Application

internal class MainApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        DiApplicationDelegate.onCreate(this)
    }
}