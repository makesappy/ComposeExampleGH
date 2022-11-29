package com.nous.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.nous.example.domain.model.Ancestry
import com.nous.example.domain.model.Classification
import com.nous.example.domain.model.Gender
import com.nous.example.domain.model.House
import java.time.LocalDate

@Entity(tableName = "character")
internal data class CharacterEntity(
    @PrimaryKey
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
    val wandLength: Double?,
    val patronus: String?,
    val classification: Classification,
    val actor: String?,
    val alternateActors: List<String>?,
    val isAlive: Boolean,
    val imageUrl: String?
)