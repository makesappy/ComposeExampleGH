package com.nous.example.ext

import androidx.annotation.StringRes
import com.nous.example.domain.model.DataCategory
import com.nous.example.prod.R

@StringRes
fun DataCategory.textResId() = when (this) {
    DataCategory.ListOfTags -> R.string.list_of_tags_title
    DataCategory.RandomImg -> R.string.random_img_title
    DataCategory.RandomGif -> R.string.random_gif_title
    DataCategory.TextToSay -> R.string.text_to_say_title
}