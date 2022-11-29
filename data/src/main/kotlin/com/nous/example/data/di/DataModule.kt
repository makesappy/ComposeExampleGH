package com.nous.example.data.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.nous.example.data.api.Api
import com.nous.example.data.controller.AndroidOverlayErrorController
import com.nous.example.data.controller.AndroidStringResourceController
import com.nous.example.data.controller.AppInitializerController
import com.nous.example.data.controller.GlobalNavigationController
import com.nous.example.data.database.ApplicationDatabase
import com.nous.example.data.database.RoomDatabaseFactory
import com.nous.example.data.repository.HarryPotterCharacterRepository
import com.nous.example.data.repository.HarryPotterSpellRepository
import com.nous.example.domain.controller.InitializerController
import com.nous.example.domain.controller.MainNavigationController
import com.nous.example.domain.controller.OverlayErrorController
import com.nous.example.domain.controller.StringResourceController
import com.nous.example.domain.repository.CharacterRepository
import com.nous.example.domain.repository.SpellRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module
import java.security.KeyStore

val dataModule = module {
    singleOf(::GlobalNavigationController) bind MainNavigationController::class
    singleOf(::AppInitializerController) bind InitializerController::class
    singleOf(::AndroidStringResourceController) bind StringResourceController::class
    singleOf(::AndroidOverlayErrorController) bind OverlayErrorController::class
    singleOf(::Api)
    singleOf(::RoomDatabaseFactory)

    single<CharacterRepository> {
        HarryPotterCharacterRepository(
            get(),
            get(),
            get<ApplicationDatabase>().characterDatabase()
        )
    }
    single<SpellRepository> {
        HarryPotterSpellRepository(
            get(),
            get(),
            get<ApplicationDatabase>().spellDatabase()
        )
    }
    single { provideSharedPreferences(get()) }
    single {
        get<RoomDatabaseFactory>().create(
            ApplicationDatabase.DATABASE_NAME,
            ApplicationDatabase::class
        )
    }
}

private fun provideSharedPreferences(context: Context): SharedPreferences {
    val fileName = "prefs"
    fun getSharedPrefs() = EncryptedSharedPreferences.create(
        context,
        fileName,
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    return runCatching {
        getSharedPrefs()
    }.recoverCatching {
        // workaround https://github.com/google/tink/issues/535#issuecomment-912170221

        // delete the master key
        val keyStore = KeyStore.getInstance("AndroidKeyStore")
        keyStore.load(null)
        keyStore.deleteEntry(MasterKey.DEFAULT_MASTER_KEY_ALIAS)

        // delete the encrypted prefs
        context.getSharedPreferences(fileName, Context.MODE_PRIVATE).edit().clear().apply()

        getSharedPrefs()
    }.getOrThrow()
}
