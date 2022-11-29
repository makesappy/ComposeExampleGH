package com.nous.example.data.database

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import androidx.room.RoomDatabase
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import java.security.SecureRandom
import kotlin.reflect.KClass

/**
 * RoomDatabaseFactory creates encrypted Room database
 */
class RoomDatabaseFactory internal constructor(
    private val applicationContext: Context,
    private val prefs: SharedPreferences
) {

    fun <T : RoomDatabase> create(databaseName: String, databaseClass: KClass<T>): T =
        Room.databaseBuilder(
            applicationContext,
            databaseClass.java,
            databaseName
        ).also {
            it.openHelperFactory(SupportFactory(SQLiteDatabase.getBytes(getEncryptionPhrase().toCharArray())))
        }.build()

    private fun getEncryptionPhrase() =
        prefs.getString(DATABASE_PHRASE, null) ?: generatePhrase()

    private fun generatePhrase() = getRandomString(SecureRandom()).also {
        prefs.edit().putString(DATABASE_PHRASE, it).apply()
    }

    private fun getRandomString(random: SecureRandom) = buildString {
        repeat(random.nextInt(RANDOM_BOUND) + MIN_SIZE) {
            append(DATA[random.nextInt(DATA.length)])
        }
    }

    companion object {
        private const val DATABASE_PHRASE: String = "database_phrase"
        private const val MIN_SIZE = 30
        private const val RANDOM_BOUND = 98
        private const val DATA: String =
            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz|!£$%&/=@#(){}"
    }
}