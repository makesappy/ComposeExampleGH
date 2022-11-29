package com.nous.example.presentation

import com.nous.example.domain.model.Classification
import com.nous.example.domain.usecase.GetCharactersByClassificationUseCase
import com.nous.example.domain.usecase.OnBackClickedUseCase
import com.nous.example.domain.usecase.SearchCharacterUseCase
import com.nous.example.domain.usecase.ShowOverlayErrorUseCase

internal class StudentsScreenViewModel(
    private val getCharactersByClassification: GetCharactersByClassificationUseCase,
    showOverlayError: ShowOverlayErrorUseCase,
    onBackClicked: OnBackClickedUseCase,
    searchCharacter: SearchCharacterUseCase
) : AbstractCharactersViewModel(showOverlayError, onBackClicked, searchCharacter) {
    override suspend fun getCharacters() = getCharactersByClassification(Classification.Student)
}