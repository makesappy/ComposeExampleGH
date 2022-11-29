package com.nous.example.domain.di

import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import com.nous.example.domain.api.HarryPotterApi
import com.nous.example.domain.usecase.*
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.factoryOf
import org.koin.core.scope.Scope
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val domainModule = module {
    factoryOf(::SetInitializedUseCase)
    factoryOf(::OpenHomeScreenUseCase)
    factoryOf(::OpenGifScreenUseCase)
    factoryOf(::OpenImgScreenUseCase)
    factoryOf(::OpenTextToSayScreenUseCase)
    factoryOf(::OpenListOfTagsScreenUseCase)
    factoryOf(::ObserveNavigationEventUseCase)
    factoryOf(::ObserveOverlayErrorUseCase)
    factoryOf(::ShowOverlayErrorUseCase)

    single { getRetrofit().create(HarryPotterApi::class.java) }
}

fun getRetrofit(): Retrofit {
    val okHttpClient = OkHttpClient.Builder().apply {
        connectTimeout(15L, TimeUnit.SECONDS)
        writeTimeout(15L, TimeUnit.SECONDS)
        readTimeout(15L, TimeUnit.SECONDS)
    }.addInterceptor(HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }).build()

    return Retrofit.Builder()
        .client(okHttpClient)
        .baseUrl("https://hp-api.herokuapp.com/")
        .addConverterFactory(Json.asConverterFactory("application/json".toMediaType()))
        .build()
}