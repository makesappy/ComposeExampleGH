package com.nous.example.domain.repository

import com.nous.example.domain.model.ResultData
import com.nous.example.domain.model.Spell
import com.nous.example.domain.model.Character
import com.nous.example.domain.model.House

internal interface HpApiRepository {

    suspend fun getCharacters(): ResultData<List<Character>>

    suspend fun getStudents(): ResultData<List<Character>>

    suspend fun getStaff(): ResultData<List<Character>>

    suspend fun getByHouse(house: House): ResultData<List<Character>>

    suspend fun getSpells(): ResultData<List<Spell>>
}