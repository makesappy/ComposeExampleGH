package com.nous.example.presentation

import com.nous.example.domain.model.House
import com.nous.example.domain.usecase.GetCharactersByHouseUseCase
import com.nous.example.domain.usecase.OnBackClickedUseCase
import com.nous.example.domain.usecase.SearchCharacterUseCase
import com.nous.example.domain.usecase.ShowOverlayErrorUseCase

internal class ByHouseScreenViewModel(
    private val getCharactersByHouse: GetCharactersByHouseUseCase,
    showOverlayError: ShowOverlayErrorUseCase,
    onBackClicked: OnBackClickedUseCase,
    searchCharacter: SearchCharacterUseCase,
    private val house: House
) : AbstractCharactersViewModel(showOverlayError, onBackClicked, searchCharacter) {
    override suspend fun getCharacters() = getCharactersByHouse(house)
}