package com.nous.example.domain.model

import java.time.LocalDate

data class Character(
    val name: String,
    val alternateNames: List<String>? = null,
    val species: String? = null,
    val gender: Gender,
    val house: House? = null,
    val dateOfBirth: LocalDate? = null,
    val isWizard: Boolean,
    val ancestry: Ancestry? = null,
    val eyeColor: String? = null,
    val hairColor: String? = null,
    val wandWood: String? = null,
    val wandCore: String? = null,
    val wandLength: Double? = null,
    val patronus: String? = null,
    val classification: Classification,
    val actor: String? = null,
    val alternateActors: List<String>? = null,
    val isAlive: Boolean,
    val imageUrl: String?
)