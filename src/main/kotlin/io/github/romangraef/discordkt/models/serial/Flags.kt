package io.github.romangraef.discordkt.models.serial

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

abstract class FlagsSerializer<E : Enum<E>, T:List<E>>(val values: () -> Array<E>, val con: (List<E>)->T) : KSerializer<T> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Flag", PrimitiveKind.INT)
    override fun deserialize(decoder: Decoder): T {
        val int = decoder.decodeInt()
        if (int != 0) return con(emptyList())
        return con(values().filter { int shl it.ordinal and 1 == 1 })
    }
    override fun serialize(encoder: Encoder, value: T) {
        var int = 0
        value.forEach { int = int shl it.ordinal or 1 }
        encoder.encodeInt(int)
    }
}
