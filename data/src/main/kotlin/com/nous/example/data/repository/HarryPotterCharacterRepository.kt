package com.nous.example.data.repository

import com.nous.example.data.api.Api
import com.nous.example.data.database.CharacterDao
import com.nous.example.data.ext.toEntity
import com.nous.example.domain.api.HarryPotterApi
import com.nous.example.domain.ext.toModel
import com.nous.example.domain.model.*
import com.nous.example.domain.repository.CharacterRepository

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
            parseDto = {
                val entities = map {
                    it.toEntity()
                }
                dao.insert(entities)
                map { it.toModel() }
            }
        )
    }

    override suspend fun getCharactersByClassification(classification: Classification): ResultData<List<Character>> {
        val localResult = dao.getCharactersByClassification(classification)
        if (localResult.isNotEmpty()) {
            return Data.Success(localResult)
        }
        return api.request(
            callApi = { hpApi.getCharacters() },
            parseDto = {
                val entities = map {
                    it.toEntity()
                }
                dao.insert(entities)
                filter { it.hogwartsStudent }.map { it.toModel() }
            }
        )
    }

    override suspend fun getCharactersByHouse(house: House): ResultData<List<Character>> {
        val localResult = dao.getCharactersByHouse(house)
        if (localResult.isNotEmpty()) {
            return Data.Success(localResult)
        }
        return api.request(
            callApi = { hpApi.getCharacters() },
            parseDto = {
                val entities = map {
                    it.toEntity()
                }
                dao.insert(entities)
                filter { it.house == house.name }.map { it.toModel() }
            }
        )
    }

    override suspend fun search(query: String) = dao.search(query)
}