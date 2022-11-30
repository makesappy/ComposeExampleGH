package com.nous.example.presentation

import com.nous.example.common.AbstractViewModel
import com.nous.example.domain.model.Character
import com.nous.example.domain.model.ResultData
import com.nous.example.domain.model.onError
import com.nous.example.domain.model.onSuccess
import com.nous.example.domain.usecase.*

internal abstract class AbstractCharactersViewModel(
    private val showOverlayError: ShowOverlayErrorUseCase,
    private val onBackClicked: OnBackClickedUseCase,
    private val searchCharacter: SearchCharacterUseCase,
    private val openCharacterDetailScreen: OpenCharacterDetailScreenUseCase,
) : AbstractViewModel<AbstractCharactersViewModel.State>(State()) {

    abstract suspend fun getCharacters(): ResultData<List<Character>>

    init {
        launchWhenActive {
            getCharacters().onSuccess {
                state = state.copy(characters = it)
            }.onError {
                showOverlayError(it)
            }
        }
    }

    fun openCharacterDetail(name: String) = openCharacterDetailScreen(name)

    fun navigateBack() = onBackClicked()

    fun search(query: String) = searchCharacter(query)

    data class State(
        val characters: List<Character> = listOf()
    ) : AbstractViewModel.State
}