package com.nous.example.domain.di

import com.nous.example.domain.usecase.*
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.module

val domainModule = module {
    factoryOf(::SetInitializedUseCase)
    factoryOf(::OpenHomeScreenUseCase)
    factoryOf(::OpenStaffScreenUseCase)
    factoryOf(::OpenStudentsScreenUseCase)
    factoryOf(::OpenSpellsScreenUseCase)
    factoryOf(::OpenAllCharactersScreenUseCase)
    factoryOf(::ObserveNavigationEventUseCase)
    factoryOf(::ObserveOverlayErrorUseCase)
    factoryOf(::ShowOverlayErrorUseCase)
    factoryOf(::GetCharactersUseCase)
    factoryOf(::OnBackClickedUseCase)
    factoryOf(::GetCharactersByHouseUseCase)
    factoryOf(::GetCharactersByClassificationUseCase)
    factoryOf(::GetSpellsUseCase)
    factoryOf(::SearchCharacterUseCase)
    factoryOf(::SearchSpellUseCase)
    factoryOf(::OpenHousesScreenUseCase)
    factoryOf(::OpenCharactersByHouseScreenUseCase)
    factoryOf(::GetCharacterByNameUseCase)
    factoryOf(::OpenCharacterDetailScreenUseCase)
    factoryOf(::OpenSpellDetailScreenUseCase)
    factoryOf(::GetSpellByNameUseCase)
}