package com.nous.example.domain.model

import java.time.LocalDate

data class Character(
    val name: String,
    val alternateNames: List<String>?,
    val species: String?,
    val gender: Gender,
    val house: House?,
    val dateOfBirth: LocalDate?,
    val isWizard: Boolean,
    val ancestry: Ancestry?,
    val eyeColour: String?,
    val hairColor: String?,
    val wandWood: String?,
    val wandCore: String?,
    val patronus: String?,
    val classification: Classification,
    val actor: String?,
    val alternateActors: List<String>?,
    val isAlive: Boolean,
    val imageUrl: String
)