package com.nous.example.domain.usecase

import com.nous.example.domain.model.Character
import com.nous.example.domain.model.Classification
import com.nous.example.domain.model.ResultData
import com.nous.example.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCharactersByClassificationUseCase(
    private val repository: CharacterRepository
) : SuspendUseCase<Classification, ResultData<List<Character>>> {
    override suspend fun invoke(input: Classification) = withContext(Dispatchers.IO) {
        repository.getCharactersByClassification(input)
    }
}