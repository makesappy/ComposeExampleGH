package com.nous.example.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nous.example.data.model.SpellEntity
import com.nous.example.domain.model.Spell
import kotlinx.coroutines.flow.Flow

@Dao
internal interface SpellDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(spells: List<SpellEntity>)

    @Query("SELECT * FROM spell")
    suspend fun getSpells(): List<Spell>

    @Query("SELECT * FROM spell WHERE name LIKE '%' || :name || '%'")
    fun search(name: String): Flow<List<Spell>>
}