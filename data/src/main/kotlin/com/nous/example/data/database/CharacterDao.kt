package com.nous.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nous.example.data.model.CharacterEntity
import com.nous.example.domain.model.Character
import com.nous.example.domain.model.Classification
import com.nous.example.domain.model.House
import kotlinx.coroutines.flow.Flow

@Dao
internal interface CharacterDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(characters: List<CharacterEntity>) : List<Long>

    @Query("SELECT * FROM character")
    suspend fun getCharacters(): List<Character>

    @Query("SELECT * FROM character WHERE name = :name")
    suspend fun getCharacter(name: String): Character

    @Query("SELECT * FROM character WHERE house = :house")
    suspend fun getCharactersByHouse(house: House): List<Character>

    @Query("SELECT * FROM character WHERE classification = :classification")
    suspend fun getCharactersByClassification(classification: Classification): List<Character>

    @Query("SELECT * FROM character WHERE name LIKE '%' || :name || '%'")
    fun search(name: String): Flow<List<Character>>
}