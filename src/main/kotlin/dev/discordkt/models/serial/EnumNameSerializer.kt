package dev.discordkt.models.serial

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

abstract class EnumNameSerializer<E : Enum<E>>(val valueOf: (String) -> E, val uppercase: Boolean = true) : KSerializer<E> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("EnumName", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): E = valueOf(decoder.decodeString().let { if (uppercase) it else it.toUpperCase() })
    override fun serialize(encoder: Encoder, value: E) = encoder.encodeString(value.name.let { if (uppercase) it else it.toLowerCase() })
}
