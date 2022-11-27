package com.nous.example.di

import com.nous.example.data.di.dataModule
import com.nous.example.domain.di.domainModule
import org.koin.core.module.Module

internal val appModules = listOf(
    dataModule,
    domainModule
)