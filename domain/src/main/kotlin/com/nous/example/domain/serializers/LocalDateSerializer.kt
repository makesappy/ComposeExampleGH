package com.nous.example.domain.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object LocalDateSerializer : KSerializer<LocalDate?> {
    private const val PATTERN = "dd-MM-yyyy"
    private val formatter = DateTimeFormatter.ofPattern(PATTERN)

    override fun deserialize(decoder: Decoder): LocalDate? {
        val string = decoder.decodeString()
        if (string.isEmpty()) {
            return null
        }
        return LocalDate.parse(decoder.decodeString(), formatter)
    }

    override val descriptor = PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDate?) {
        val string = value?.format(formatter) ?: ""
        encoder.encodeString(string)
    }
}