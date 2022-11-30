package com.nous.example.domain.usecase

import com.nous.example.domain.model.ResultData
import com.nous.example.domain.model.Spell
import com.nous.example.domain.repository.SpellRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetSpellByNameUseCase(
    private val repository: SpellRepository
) : SuspendUseCase<String, ResultData<Spell>> {
    override suspend fun invoke(input: String) = withContext(Dispatchers.IO) {
        repository.getSpell(input)
    }
}