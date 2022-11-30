package com.nous.example.domain.repository

import com.nous.example.domain.model.Character
import com.nous.example.domain.model.Classification
import com.nous.example.domain.model.House
import com.nous.example.domain.model.ResultData

interface CharacterRepository : SearchRepository<Character> {
    suspend fun getCharacters(): ResultData<List<Character>>

    suspend fun getCharacter(name: String): ResultData<Character>

    suspend fun getCharactersByClassification(classification: Classification): ResultData<List<Character>>

    suspend fun getCharactersByHouse(house: House): ResultData<List<Character>>
}