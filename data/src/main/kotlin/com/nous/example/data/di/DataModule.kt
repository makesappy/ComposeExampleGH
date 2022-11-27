package com.nous.example.data.di

import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.nous.example.data.controller.GlobalNavigationController
import com.nous.example.data.controller.AppInitializerController
import com.nous.example.domain.controller.MainNavigationController
import com.nous.example.domain.controller.InitializerController
import org.koin.dsl.bind

val dataModule = module {
    singleOf(::GlobalNavigationController) bind MainNavigationController::class
    singleOf(::AppInitializerController) bind InitializerController::class
}