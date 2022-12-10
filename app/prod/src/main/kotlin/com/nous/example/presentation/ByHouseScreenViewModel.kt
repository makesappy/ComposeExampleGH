package com.nous.example.presentation

import com.nous.example.domain.model.Character
import com.nous.example.domain.model.House
import com.nous.example.domain.usecase.*

internal class ByHouseScreenViewModel(
    private val getCharactersByHouse: GetCharactersByHouseUseCase,
    showOverlayError: ShowOverlayErrorUseCase,
    onBackClicked: OnBackClickedUseCase,
    private val searchCharacter: SearchCharacterUseCase,
    private val openCharacterDetailScreen: OpenCharacterDetailScreenUseCase,
    private val house: House
) : AbstractSearchViewModel<Character>(showOverlayError, onBackClicked) {
    override suspend fun get() = getCharactersByHouse(house)
    override fun search(query: String) =
        searchCharacter(SearchCharacterUseCase.Param(query) { it.house == house })

    override fun openDetail(name: String) = openCharacterDetailScreen(name)
}