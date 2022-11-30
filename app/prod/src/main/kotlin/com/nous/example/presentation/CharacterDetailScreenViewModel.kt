package com.nous.example.presentation

import com.nous.example.common.AbstractViewModel
import com.nous.example.domain.model.Character
import com.nous.example.domain.model.onError
import com.nous.example.domain.model.onSuccess
import com.nous.example.domain.usecase.GetCharacterByNameUseCase
import com.nous.example.domain.usecase.OnBackClickedUseCase
import com.nous.example.domain.usecase.ShowOverlayErrorUseCase
import com.nous.example.domain.usecase.invoke

internal class CharacterDetailScreenViewModel(
    private val getCharacterByName: GetCharacterByNameUseCase,
    private val showOverlayError: ShowOverlayErrorUseCase,
    private val onBackClicked: OnBackClickedUseCase,
    private val name: String
) : AbstractViewModel<CharacterDetailScreenViewModel.State>(State()) {
    init {
        launchWhenActive {
            getCharacterByName(name).onSuccess {
                state = state.copy(character = it)
            }.onError {
                showOverlayError(it)
            }
        }
    }

    fun onBack() = onBackClicked()

    data class State(
        val character: Character? = null
    ) : AbstractViewModel.State
}