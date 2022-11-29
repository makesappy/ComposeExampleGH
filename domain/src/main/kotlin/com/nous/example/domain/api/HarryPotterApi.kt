package com.nous.example.domain.api

import com.nous.example.domain.model.CharacterDto
import com.nous.example.domain.model.Spell
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface HarryPotterApi {
    @GET("api/characters")
    suspend fun getCharacters(): Response<List<CharacterDto>>

    @GET("api/spells")
    suspend fun getSpells(): Response<List<Spell>>
}