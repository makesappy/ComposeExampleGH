package com.nous.example.data.model

@kotlinx.serialization.Serializable
internal data class WandDto(
    val wood: String,
    val core: String,
    val length: Double?
)