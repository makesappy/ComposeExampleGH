package com.nous.example.domain.repository

import kotlinx.coroutines.flow.Flow

interface SearchRepository<T> {
    suspend fun search(query: String): Flow<List<T>>
}