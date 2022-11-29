package com.nous.example.data.model

import androidx.room.ColumnInfo
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
    @ColumnInfo(name = "alternate_names")
    val alternateNames: List<String>?,
    val species: String?,
    val gender: Gender,
    val house: House?,
    @ColumnInfo(name = "date_of_birth")
    val dateOfBirth: LocalDate?,
    @ColumnInfo(name = "is_wizard")
    val isWizard: Boolean,
    val ancestry: Ancestry?,
    @ColumnInfo(name = "eye_color")
    val eyeColour: String?,
    @ColumnInfo(name = "hair_color")
    val hairColor: String?,
    @ColumnInfo(name = "wand_wood")
    val wandWood: String?,
    @ColumnInfo(name = "wand_core")
    val wandCore: String?,
    val patronus: String?,
    val classification: Classification,
    val actor: String?,
    @ColumnInfo(name = "alternate_actors")
    val alternateActors: List<String>?,
    @ColumnInfo(name = "is_alive")
    val isAlive: Boolean,
    @ColumnInfo(name = "image_url")
    val imageUrl: String
)