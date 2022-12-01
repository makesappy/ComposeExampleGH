package com.nous.example.data.database

import androidx.room.TypeConverter
import com.nous.example.data.model.dateTimeFormatter
import com.nous.example.domain.model.*
import java.time.LocalDate

private const val listSeparator = ","

internal object CommonTypeConverter {
    @TypeConverter
    fun fromList(obj: List<String>?) = obj?.joinToString(listSeparator)

    @TypeConverter
    fun toList(string: String?) = string?.split(listSeparator)

    @TypeConverter
    fun fromClassification(obj: Classification?) = obj?.name

    @TypeConverter
    fun toClassification(string: String?) = string?.let { Classification.valueOf(it) }

    @TypeConverter
    fun fromAncestry(obj: Ancestry?) = obj?.name

    @TypeConverter
    fun toAncestry(string: String?) = string?.let { Ancestry.valueOf(it) }

    @TypeConverter
    fun fromHouse(obj: House?) = obj?.name

    @TypeConverter
    fun toHouse(string: String?) = string?.let { House.valueOf(it) }

    @TypeConverter
    fun fromGender(obj: Gender) = obj.name

    @TypeConverter
    fun toGender(string: String) = Gender.valueOf(string)

    @TypeConverter
    fun fromLocalDate(obj: LocalDate?) = obj?.format(dateTimeFormatter)

    @TypeConverter
    fun toLocalDate(string: String?) =
        string?.let { LocalDate.parse(it, dateTimeFormatter) }
}