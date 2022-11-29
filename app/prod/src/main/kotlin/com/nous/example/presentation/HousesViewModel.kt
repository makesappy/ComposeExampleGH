package com.nous.example.presentation

import com.nous.example.common.AbstractViewModel
import com.nous.example.domain.model.House
import com.nous.example.domain.usecase.OnBackClickedUseCase
import com.nous.example.domain.usecase.OpenCharactersByHouseScreenUseCase
import com.nous.example.domain.usecase.invoke

internal class HousesViewModel(
    private val onBackClicked: OnBackClickedUseCase,
    private val openCharactersByHouseScreen: OpenCharactersByHouseScreenUseCase
) : AbstractViewModel<HousesViewModel.State>(State()) {

    fun onBack() = onBackClicked()
    fun openHouse(house: House) = openCharactersByHouseScreen(house)

    data class State(
        val houses: List<House> = House.values().toList()
    ) : AbstractViewModel.State
}