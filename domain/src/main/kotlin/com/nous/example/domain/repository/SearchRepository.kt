package com.nous.example.domain.repository

import kotlinx.coroutines.flow.Flow

interface SearchRepository<T> {
    fun search(query: String): Flow<List<T>>
}