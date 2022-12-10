package com.nous.example.presentation

import com.nous.example.domain.model.Character
import com.nous.example.domain.model.Classification
import com.nous.example.domain.usecase.*

internal class StudentsScreenViewModel(
    private val getCharactersByClassification: GetCharactersByClassificationUseCase,
    showOverlayError: ShowOverlayErrorUseCase,
    onBackClicked: OnBackClickedUseCase,
    private val searchCharacter: SearchCharacterUseCase,
    private val openCharacterDetailScreen: OpenCharacterDetailScreenUseCase
) : AbstractSearchViewModel<Character>(showOverlayError, onBackClicked) {
    override suspend fun get() = getCharactersByClassification(Classification.Student)
    override fun search(query: String) =
        searchCharacter(SearchCharacterUseCase.Param(query) { it.classification == Classification.Student })

    override fun openDetail(name: String) = openCharacterDetailScreen(name)
}