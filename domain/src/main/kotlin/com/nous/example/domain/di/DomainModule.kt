package com.nous.example.domain.di

import com.nous.example.domain.usecase.*
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::SetInitializedUseCase)
    factoryOf(::OpenHomeScreenUseCase)
    factoryOf(::OpenGifScreenUseCase)
    factoryOf(::OpenImgScreenUseCase)
    factoryOf(::OpenTextToSayScreenUseCase)
    factoryOf(::OpenListOfTagsScreenUseCase)
    factoryOf(::ObserveNavigationEventUseCase)
    factoryOf(::ObserveOverlayErrorUseCase)
    factoryOf(::ShowOverlayErrorUseCase)
}