package com.nous.example.data.model

import androidx.room.Entity

@Entity("spell")
data class SpellEntity(
    val name: String,
    val description: String
)
