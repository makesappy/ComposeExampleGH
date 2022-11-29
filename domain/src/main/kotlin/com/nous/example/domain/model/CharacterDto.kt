package com.nous.example.domain.model

import com.nous.example.domain.serializers.LocalDateSerializer
import java.time.LocalDate

@kotlinx.serialization.Serializable
internal data class CharacterDto(
    val name: String,
    val alternate_names: List<String>,
    val species: String,
    val gender: String,
    val house: String?,
    @kotlinx.serialization.Serializable(with = LocalDateSerializer::class)
    val dateOfBirth: LocalDate?,
    val wizard: Boolean,
    val ancestry: String?,
    val eyeColour: String,
    val hairColor: String,
    val wand: WandDto?,
    val patronus: String?,
    val hogwartsStudent: Boolean,
    val hogwartsStaff: Boolean,
    val actor: String?,
    val alternate_actors: List<String>,
    val alive: Boolean,
    val image: String
)