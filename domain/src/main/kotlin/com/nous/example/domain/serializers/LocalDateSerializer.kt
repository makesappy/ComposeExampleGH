package com.nous.example.domain.serializers

import com.nous.example.domain.model.dateTimeFormatter
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate

object LocalDateSerializer : KSerializer<LocalDate?> {

    override fun deserialize(decoder: Decoder): LocalDate? {
        val string = decoder.decodeString()
        if (string.isEmpty()) {
            return null
        }
        return LocalDate.parse(decoder.decodeString(), dateTimeFormatter)
    }

    override val descriptor = PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: LocalDate?) {
        val string = value?.format(dateTimeFormatter) ?: ""
        encoder.encodeString(string)
    }
}