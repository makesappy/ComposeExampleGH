package com.nous.example.domain.usecase

import com.nous.example.domain.model.Character
import com.nous.example.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class SearchCharacterUseCase(
    private val repository: CharacterRepository
) : SynchronousUseCase<String, Flow<List<Character>>> {
    override fun invoke(input: String) = repository.search(input)
}