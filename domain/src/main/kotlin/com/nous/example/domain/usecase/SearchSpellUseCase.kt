package com.nous.example.domain.usecase

import com.nous.example.domain.model.Spell
import com.nous.example.domain.repository.SpellRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SearchSpellUseCase(
    private val repository: SpellRepository
) : SuspendUseCase<String, Flow<List<Spell>>> {
    override suspend fun invoke(input: String) = withContext(Dispatchers.IO) {
        repository.search(input)
    }
}