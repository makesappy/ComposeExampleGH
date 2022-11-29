package com.nous.example.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("spell")
internal data class SpellEntity(
    @PrimaryKey
    val name: String,
    val description: String
)
