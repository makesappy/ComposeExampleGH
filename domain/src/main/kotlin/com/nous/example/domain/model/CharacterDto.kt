package com.nous.example.domain.model

@kotlinx.serialization.Serializable
data class CharacterDto(
    val name: String,
    val alternate_names: List<String>,
    val species: String,
    val gender: String,
    val house: String?,
    val dateOfBirth: String,
    val wizard: Boolean,
    val ancestry: String,
    val eyeColour: String,
    val hairColour: String,
    val wand: WandDto,
    val patronus: String,
    val hogwartsStudent: Boolean,
    val hogwartsStaff: Boolean,
    val actor: String?,
    val alternate_actors: List<String>,
    val alive: Boolean,
    val image: String
)