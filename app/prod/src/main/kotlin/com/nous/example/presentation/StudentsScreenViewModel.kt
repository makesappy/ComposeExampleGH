package com.nous.example.presentation

import com.nous.example.domain.model.Classification
import com.nous.example.domain.usecase.*

internal class StudentsScreenViewModel(
    private val getCharactersByClassification: GetCharactersByClassificationUseCase,
    showOverlayError: ShowOverlayErrorUseCase,
    onBackClicked: OnBackClickedUseCase,
    searchCharacter: SearchCharacterUseCase,
    openCharacterDetailScreen: OpenCharacterDetailScreenUseCase,
) : AbstractCharactersViewModel(
    showOverlayError,
    onBackClicked,
    searchCharacter,
    openCharacterDetailScreen
) {
    override suspend fun getCharacters() = getCharactersByClassification(Classification.Student)
}