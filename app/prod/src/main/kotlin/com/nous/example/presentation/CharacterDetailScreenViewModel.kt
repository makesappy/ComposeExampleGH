package com.nous.example.presentation

import com.nous.example.common.AbstractViewModel
import com.nous.example.domain.model.Character

internal class CharacterDetailScreenViewModel(

) : AbstractViewModel<CharacterDetailScreenViewModel.State>(State()) {
    data class State(
        val character: Character? = null
    ) :AbstractViewModel.State
}