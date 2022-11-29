package com.nous.example.domain.model

enum class House {
    Gryffindor,
    Hufflepuff,
    Ravenclaw,
    Slytherin;

    val imageUrl: String
        get() = when (this) {
            Gryffindor -> "https://cdn.shortpixel.ai/spai/q_lossy+w_672+h_672+to_auto+ret_img/https://quizpin.com/wp-content/uploads/2020/10/EQ-5CjEXYAIBra2.jpg"
            Hufflepuff -> "https://cdn.shopify.com/s/files/1/1541/8579/files/Hufflepuff-harry_potter_large.JPG?v=1491538917"
            Ravenclaw -> "https://www.hp-lexicon.org/wp-content/uploads/2015/08/shield_rav-200x0-c-default.jpg"
            Slytherin -> "https://miro.medium.com/max/500/0*m0pYj25tg4A2mAfU.png"
        }
}