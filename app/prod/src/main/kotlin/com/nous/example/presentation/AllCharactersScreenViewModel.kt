package com.nous.example.presentation

import com.nous.example.domain.model.Character
import com.nous.example.domain.usecase.*

internal class AllCharactersScreenViewModel(
    private val getCharactersUseCase: GetCharactersUseCase,
    showOverlayError: ShowOverlayErrorUseCase,
    onBackClicked: OnBackClickedUseCase,
    private val searchCharacter: SearchCharacterUseCase,
    private val openCharacterDetailScreen: OpenCharacterDetailScreenUseCase,
) : AbstractSearchViewModel<Character>(showOverlayError, onBackClicked) {
    override suspend fun get() = getCharactersUseCase()
    override fun search(query: String) = searchCharacter(SearchCharacterUseCase.Param(query))
    override fun openDetail(name: String) = openCharacterDetailScreen(name)
}