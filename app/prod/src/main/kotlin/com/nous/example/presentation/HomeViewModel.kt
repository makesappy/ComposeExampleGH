package com.nous.example.presentation

import com.nous.example.common.AbstractViewModel
import com.nous.example.domain.model.ShowCategory
import com.nous.example.domain.usecase.*

internal class HomeViewModel(
    private val openAllCharactersScreen: OpenAllCharactersScreenUseCase,
    private val openSpellsScreen: OpenSpellsScreenUseCase,
    private val openStudentsScreen: OpenStudentsScreenUseCase,
    private val openStaffScreen: OpenStaffScreenUseCase,
    private val openHousesScreen: OpenHousesScreenUseCase
) : AbstractViewModel<HomeViewModel.State>(State()) {

    fun openCategory(category: ShowCategory) = when (category) {
        ShowCategory.All -> openAllCharactersScreen()
        ShowCategory.Spells -> openSpellsScreen()
        ShowCategory.Students -> openStudentsScreen()
        ShowCategory.Staff -> openStaffScreen()
        ShowCategory.Houses -> openHousesScreen()
    }

    data class State(
        val categories: List<ShowCategory> = ShowCategory.values().toList()
    ) : AbstractViewModel.State
}