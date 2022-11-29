package com.nous.example.domain.model

@kotlinx.serialization.Serializable
data class WandDto(
    val wood: String,
    val core: String,
    val length: Double?
)