package com.nous.example.presentation

import com.nous.example.common.AbstractViewModel
import com.nous.example.domain.model.Spell
import com.nous.example.domain.model.onError
import com.nous.example.domain.model.onSuccess
import com.nous.example.domain.usecase.GetSpellByNameUseCase
import com.nous.example.domain.usecase.OnBackClickedUseCase
import com.nous.example.domain.usecase.ShowOverlayErrorUseCase
import com.nous.example.domain.usecase.invoke

internal class SpellDetailViewModel(
    private val getSpell: GetSpellByNameUseCase,
    private val onBackClicked: OnBackClickedUseCase,
    private val showOverlayError: ShowOverlayErrorUseCase,
    private val name: String
) : AbstractViewModel<SpellDetailViewModel.State>(State()) {
    init {
        launchWhenActive {
            getSpell(name).onSuccess {
                state = state.copy(spell = it)
            }.onError { showOverlayError(it) }
        }
    }

    fun onBack() = onBackClicked()

    data class State(
        val spell: Spell? = null
    ) : AbstractViewModel.State
}