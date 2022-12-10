package com.nous.example.domain.usecase

import com.nous.example.domain.model.Character
import com.nous.example.domain.repository.CharacterRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SearchCharacterUseCase(
    private val repository: CharacterRepository
) : SynchronousUseCase<SearchCharacterUseCase.Param, Flow<List<Character>>> {
    override fun invoke(input: Param) =
        repository.search(input.query).map { it.filter(input.predicate) }

    data class Param(
        val query: String,
        val predicate: (Character) -> Boolean = { true }
    )
}