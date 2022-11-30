package com.nous.example.data.repository

import com.nous.example.data.api.Api
import com.nous.example.data.database.CharacterDao
import com.nous.example.data.ext.toEntity
import com.nous.example.data.ext.toModel
import com.nous.example.data.model.CharacterEntity
import com.nous.example.domain.api.HarryPotterApi
import com.nous.example.domain.model.*
import com.nous.example.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flowOn

internal class HarryPotterCharacterRepository(
    private val api: Api,
    private val hpApi: HarryPotterApi,
    private val dao: CharacterDao
) : CharacterRepository {
    override suspend fun getCharacters(): ResultData<List<Character>> {
        val localResult = dao.getCharacters()
        if (localResult.isNotEmpty()) {
            return Data.Success(localResult)
        }
        return api.request(
            callApi = { hpApi.getCharacters() },
            parseDto = { parseDto() }
        )
    }

    override suspend fun getCharacter(name: String) =
        runCatching { dao.getCharacter(name) }.foldToData()

    override suspend fun getCharactersByClassification(classification: Classification): ResultData<List<Character>> {
        val localResult = dao.getCharactersByClassification(classification)
        if (localResult.isNotEmpty()) {
            return Data.Success(localResult)
        }
        return api.request(
            callApi = { hpApi.getCharacters() },
            parseDto = { parseDto { it.classification == classification } }
        )
    }

    override suspend fun getCharactersByHouse(house: House): ResultData<List<Character>> {
        val localResult = dao.getCharactersByHouse(house)
        if (localResult.isNotEmpty()) {
            return Data.Success(localResult)
        }
        return api.request(
            callApi = { hpApi.getCharacters() },
            parseDto = { parseDto { it.house == house } }
        )
    }

    private suspend fun List<CharacterDto>.parseDto(predicate: ((CharacterEntity) -> Boolean) = { true }): List<Character> {
        val entities = map {
            it.toEntity()
        }
        return if (dao.insert(entities).none { it < 0 }) {
            entities.filter(predicate).map { it.toModel() }
        } else {
            error("Database insertion failed")
        }
    }

    override fun search(query: String) = dao.search(query).flowOn(Dispatchers.IO)
}