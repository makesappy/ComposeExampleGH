package com.nous.example.domain.usecase

import com.nous.example.domain.model.Character
import com.nous.example.domain.model.ResultData
import com.nous.example.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCharactersUseCase(
    private val repository: CharacterRepository
) : SuspendUseCase<Unit, ResultData<List<Character>>> {
    override suspend fun invoke(input: Unit) = withContext(Dispatchers.IO) {
        repository.getCharacters()
    }
}