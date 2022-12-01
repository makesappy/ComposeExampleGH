package com.nous.example.data.api

import com.nous.example.data.model.CharacterDto
import com.nous.example.domain.model.Spell
import retrofit2.Response
import retrofit2.http.GET

internal interface HarryPotterApi {
    @GET("api/characters")
    suspend fun getCharacters(): Response<List<CharacterDto>>

    @GET("api/spells")
    suspend fun getSpells(): Response<List<Spell>>
}