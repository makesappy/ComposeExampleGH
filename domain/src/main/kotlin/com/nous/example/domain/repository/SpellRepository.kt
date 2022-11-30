package com.nous.example.domain.repository

import com.nous.example.domain.model.ResultData
import com.nous.example.domain.model.Spell

interface SpellRepository : SearchRepository<Spell> {
    suspend fun getSpells(): ResultData<List<Spell>>
    suspend fun getSpell(name: String): ResultData<Spell>
}