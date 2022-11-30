package com.nous.example.presentation

import com.nous.example.domain.model.Spell
import com.nous.example.domain.usecase.*

internal class SpellsScreenViewModel(
    private val getSpells: GetSpellsUseCase,
    showOverlayError: ShowOverlayErrorUseCase,
    onBackClicked: OnBackClickedUseCase,
    private val searchSpell: SearchSpellUseCase,
    private val openSpellDetail: OpenSpellDetailScreenUseCase,
) : AbstractSearchViewModel<Spell>(showOverlayError, onBackClicked) {
    override suspend fun get() = getSpells()
    override fun search(query: String) = searchSpell(query)
    override fun openDetail(name: String) = openSpellDetail(name)
}