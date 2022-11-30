package com.nous.example.di

import com.nous.example.data.di.dataModule
import com.nous.example.domain.di.domainModule
import com.nous.example.domain.model.House
import com.nous.example.presentation.*
import com.nous.example.system.SplashScreenDelegate
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

private val appModule = module {
    viewModelOf(::SplashScreenViewModel)
    viewModelOf(::MainViewModel)
    viewModelOf(::AllCharactersScreenViewModel)
    viewModelOf(::StaffScreenViewModel)
    viewModelOf(::StudentsScreenViewModel)
    viewModelOf(::HomeViewModel)
    viewModelOf(::HousesViewModel)
    viewModelOf(::SpellsScreenViewModel)
    viewModel { (name: String) -> CharacterDetailScreenViewModel(get(), get(), get(), name) }
    viewModel { (name: String) -> SpellDetailViewModel(get(), get(), get(), name) }
    viewModel { (house: House) -> ByHouseScreenViewModel(get(), get(), get(), get(), get(), house) }

    singleOf(::SplashScreenDelegate)
}

internal val appModules = listOf(
    appModule,
    dataModule,
    domainModule
)