package com.nous.example.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nous.example.data.model.CharacterEntity
import com.nous.example.data.model.SpellEntity

@Database(
    entities = [
        CharacterEntity::class,
        SpellEntity::class
    ],
    exportSchema = true,
    version = 1
)
@TypeConverters(CommonTypeConverter::class)
internal abstract class ApplicationDatabase : RoomDatabase() {
    abstract fun characterDatabase(): CharacterDao
    abstract fun spellDatabase(): SpellDao

    companion object {
        internal const val DATABASE_NAME = "app_database.db"
    }
}