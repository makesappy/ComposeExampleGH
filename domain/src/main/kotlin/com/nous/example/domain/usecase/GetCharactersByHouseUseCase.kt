package com.nous.example.domain.usecase

import com.nous.example.domain.model.Character
import com.nous.example.domain.model.House
import com.nous.example.domain.model.ResultData
import com.nous.example.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GetCharactersByHouseUseCase(
    private val repository: CharacterRepository
) : SuspendUseCase<House, ResultData<List<Character>>> {
    override suspend fun invoke(input: House) = withContext(Dispatchers.IO) {
        repository.getCharactersByHouse(input)
    }
}