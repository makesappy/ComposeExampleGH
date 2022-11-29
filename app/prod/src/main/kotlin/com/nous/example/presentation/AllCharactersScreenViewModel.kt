package com.nous.example.presentation

import com.nous.example.domain.usecase.*

internal class AllCharactersScreenViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    showOverlayError: ShowOverlayErrorUseCase,
    onBackClicked: OnBackClickedUseCase,
    searchCharacter: SearchCharacterUseCase
) : AbstractCharactersViewModel(showOverlayError, onBackClicked, searchCharacter) {
    override suspend fun getCharacters() = getCharactersUseCase()
}