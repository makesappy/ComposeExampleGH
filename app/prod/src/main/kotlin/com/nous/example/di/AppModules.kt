package com.nous.example.di

import com.nous.example.data.di.dataModule
import com.nous.example.domain.di.domainModule
import com.nous.example.presentation.HomeViewModel
import com.nous.example.presentation.MainViewModel
import com.nous.example.presentation.SplashScreenViewModel
import com.nous.example.system.DiApplicationDelegate
import com.nous.example.system.SplashScreenDelegate
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.Module
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private val appModule = module {
    viewModelOf(::SplashScreenViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::HomeViewModel)
    singleOf(::SplashScreenDelegate)
}

internal val appModules = listOf(
    appModule,
    dataModule,
    domainModule
)