package com.nous.example.domain.ext

import com.nous.example.domain.model.*

internal fun CharacterDto.toModel() = Character(
    name = name,
    alternateNames = alternate_names.ifEmpty { null },
    species = species.capitalize.ifBlank { null },
    gender = Gender.valueOf(gender.capitalize),
    house = runCatching { House.valueOf(requireNotNull(house)) }.getOrNull(),
    dateOfBirth = dateOfBirth,
    isWizard = wizard,
    ancestry = when (ancestry) {
        "half-blood" -> Ancestry.HalfBlood
        "muggleborn" -> Ancestry.MuggleBorn
        "pure-blood" -> Ancestry.PureBlood
        "muggle" -> Ancestry.Muggle
        "squib" -> Ancestry.Squib
        "half-veela" -> Ancestry.HalfVeela
        "quarter-veela" -> Ancestry.QuaterVeela
        else -> null
    },
    eyeColour = eyeColour.capitalize.ifBlank { null },
    hairColor = hairColor.capitalize.ifBlank { null },
    wandWood = wand?.wood?.capitalize?.ifBlank { null },
    wandCore = wand?.core?.capitalize?.ifBlank { null },
    patronus = patronus?.capitalize?.ifBlank { null },
    classification = when {
        hogwartsStaff -> Classification.Staff
        hogwartsStudent -> Classification.Student
        else -> Classification.Other
    },
    actor = actor?.ifBlank { null },
    alternateActors = alternate_actors.ifEmpty { null },
    isAlive = alive,
    imageUrl = image
)