package com.nous.example.presentation

import com.nous.example.domain.model.House
import com.nous.example.domain.usecase.*

internal class ByHouseScreenViewModel(
    private val getCharactersByHouse: GetCharactersByHouseUseCase,
    showOverlayError: ShowOverlayErrorUseCase,
    onBackClicked: OnBackClickedUseCase,
    searchCharacter: SearchCharacterUseCase,
    openCharacterDetailScreen: OpenCharacterDetailScreenUseCase,
    private val house: House
) : AbstractCharactersViewModel(showOverlayError, onBackClicked, searchCharacter,openCharacterDetailScreen) {
    override suspend fun getCharacters() = getCharactersByHouse(house)
}