package com.nous.example.domain.model

@kotlinx.serialization.Serializable
data class Spell(
    val name: String,
    val description: String
)