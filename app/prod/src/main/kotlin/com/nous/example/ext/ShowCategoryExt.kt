package com.nous.example.ext

import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import com.nous.example.domain.model.ShowCategory
import com.nous.example.prod.R

@Composable
@StringRes
internal fun ShowCategory.titleResId() = when (this) {
    ShowCategory.All -> R.string.all_characters_category_title
    ShowCategory.Students -> R.string.students_category_title
    ShowCategory.Staff -> R.string.staff_category_title
    ShowCategory.Houses -> R.string.houses_category_title
    ShowCategory.Spells -> R.string.spells_category_title
}