package com.nous.example.domain.di

import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module
import com.nous.example.domain.usecase.SetInitializedUseCase

val domainModule = module {
    factoryOf(::SetInitializedUseCase)
}