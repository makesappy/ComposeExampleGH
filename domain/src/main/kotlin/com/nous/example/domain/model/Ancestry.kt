package com.nous.example.domain.model

enum class Ancestry {
    HalfBlood,
    MuggleBorn,
    PureBlood,
    Muggle,
    Squib,
    HalfVeela,
    QuaterVeela;

    val asString: String
        get() = when (this) {
            HalfBlood -> "Half Blood"
            MuggleBorn -> "Muggle Born"
            PureBlood -> "Pure Blood"
            Muggle -> name
            Squib -> name
            HalfVeela -> "Half Veela"
            QuaterVeela -> "Quater Veela"
        }
}