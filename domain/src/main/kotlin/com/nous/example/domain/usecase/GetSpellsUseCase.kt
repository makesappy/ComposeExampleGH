package com.nous.example.domain.usecase

import com.nous.example.domain.model.ResultData
import com.nous.example.domain.model.Spell
import com.nous.example.domain.repository.SpellRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetSpellsUseCase(
    private val repository: SpellRepository
) : SuspendUseCase<Unit, ResultData<List<Spell>>> {
    override suspend fun invoke(input: Unit) = withContext(Dispatchers.IO) {
        repository.getSpells()
    }
}