package com.nous.example.domain.ext

import com.nous.example.domain.model.*
import java.time.LocalDate

fun CharacterDto.toModel() = Character(
    name = name,
    alternateNames = alternate_names.ifEmpty { null },
    species = species.capitalize.ifBlank { null },
    gender = Gender.valueOf(gender.capitalize),
    house = runCatching { House.valueOf(requireNotNull(house)) }.getOrNull(),
    dateOfBirth = dateOfBirth.ifBlank { null }?.let { LocalDate.parse(it, dateTimeFormatter) },
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
    hairColor = hairColour.capitalize.ifBlank { null },
    wandWood = wand.wood.capitalize.ifBlank { null },
    wandCore = wand.core.capitalize.ifBlank { null },
    wandLength = wand.length,
    patronus = patronus.capitalize.ifBlank { null },
    classification = when {
        hogwartsStaff -> Classification.Staff
        hogwartsStudent -> Classification.Student
        else -> Classification.Other
    },
    actor = actor?.ifBlank { null },
    alternateActors = alternate_actors.ifEmpty { null },
    isAlive = alive,
    imageUrl = image.ifBlank { null }
)