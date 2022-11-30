package com.nous.example.domain.usecase

import com.nous.example.domain.model.Character
import com.nous.example.domain.model.Classification
import com.nous.example.domain.model.ResultData
import com.nous.example.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCharacterByNameUseCase(
    private val repository: CharacterRepository
) : SuspendUseCase<String, ResultData<Character>> {
    override suspend fun invoke(input: String) = withContext(Dispatchers.IO) {
        repository.getCharacter(input)
    }
}