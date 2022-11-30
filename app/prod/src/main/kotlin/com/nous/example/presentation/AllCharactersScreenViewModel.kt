package com.nous.example.presentation

import com.nous.example.domain.usecase.*

internal class AllCharactersScreenViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    showOverlayError: ShowOverlayErrorUseCase,
    onBackClicked: OnBackClickedUseCase,
    searchCharacter: SearchCharacterUseCase,
    openCharacterDetailScreen: OpenCharacterDetailScreenUseCase,
) : AbstractCharactersViewModel(showOverlayError, onBackClicked, searchCharacter,openCharacterDetailScreen) {
    override suspend fun getCharacters() = getCharactersUseCase()
}