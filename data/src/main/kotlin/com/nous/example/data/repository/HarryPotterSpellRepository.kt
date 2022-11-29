package com.nous.example.data.repository

import com.nous.example.data.api.Api
import com.nous.example.data.database.SpellDao
import com.nous.example.data.ext.toEntity
import com.nous.example.domain.api.HarryPotterApi
import com.nous.example.domain.model.Data
import com.nous.example.domain.model.ResultData
import com.nous.example.domain.model.Spell
import com.nous.example.domain.repository.SpellRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn

internal class HarryPotterSpellRepository(
    private val api: Api,
    private val hpApi: HarryPotterApi,
    private val dao: SpellDao
) : SpellRepository {
    override suspend fun getSpells(): ResultData<List<Spell>> {
        val localResult = dao.getSpells()
        if (localResult.isNotEmpty()) {
            return Data.Success(localResult)
        }
        return api.request(
            callApi = { hpApi.getSpells() },
            parseDto = {
                val entities = map {
                    it.toEntity()
                }
                dao.insert(entities)
                this
            }
        )
    }

    override fun search(query: String) = dao.search(query).flowOn(Dispatchers.IO)
}