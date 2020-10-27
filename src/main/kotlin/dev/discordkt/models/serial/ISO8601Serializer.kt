package dev.discordkt.models.serial

import java.time.OffsetDateTime
import java.time.format.DateTimeFormatter
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class ISO8601Serializer : KSerializer<OffsetDateTime> {
    private val FORMATTER = DateTimeFormatter.ISO_OFFSET_DATE_TIME

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("ISO8601Serializer", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): OffsetDateTime =
        FORMATTER.parse(decoder.decodeString(), OffsetDateTime::from)

    override fun serialize(encoder: Encoder, value: OffsetDateTime) {
        encoder.encodeString(FORMATTER.format(value))
    }
}
