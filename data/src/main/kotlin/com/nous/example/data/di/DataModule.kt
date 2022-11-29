package com.nous.example.data.di

import com.nous.example.data.api.Api
import com.nous.example.data.controller.AndroidOverlayErrorController
import com.nous.example.data.controller.AndroidStringResourceController
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import com.nous.example.data.controller.GlobalNavigationController
import com.nous.example.data.controller.AppInitializerController
import com.nous.example.domain.controller.MainNavigationController
import com.nous.example.domain.controller.InitializerController
import com.nous.example.domain.controller.OverlayErrorController
import com.nous.example.domain.controller.StringResourceController
import org.koin.dsl.bind

val dataModule = module {
    singleOf(::GlobalNavigationController) bind MainNavigationController::class
    singleOf(::AppInitializerController) bind InitializerController::class
    singleOf(::AndroidStringResourceController) bind StringResourceController::class
    singleOf(::AndroidOverlayErrorController) bind OverlayErrorController::class
    singleOf(::Api)
}