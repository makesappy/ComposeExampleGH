package com.nous.example.domain.ext

import java.util.*

val String.capitalize get() = replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }