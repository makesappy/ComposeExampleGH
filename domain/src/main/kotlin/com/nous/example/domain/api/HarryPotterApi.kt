package com.nous.example.domain.api

import retrofit2.Response
import retrofit2.http.GET

interface HarryPotterApi {
    @GET("api/characters")
    suspend fun getCharacters() : Response<List<String>>
}