package io.github.romangraef.discordkt.models.serial

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

abstract class NameEnumSerializer<E : Enum<E>>(name: String, val valueOf: (String) -> E) : KSerializer<E> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor(name, PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): E = valueOf(decoder.decodeString())
    override fun serialize(encoder: Encoder, value: E) = encoder.encodeString(value.name)
}
