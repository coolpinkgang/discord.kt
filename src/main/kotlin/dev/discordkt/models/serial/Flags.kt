package dev.discordkt.models.serial

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

abstract class FlagsSerializer<E : Enum<E>, T : List<E>>(val values: Array<E>, val con: (List<E>) -> T) :
    KSerializer<T> {
    constructor(values: () -> Array<E>, con: (List<E>) -> T) : this(values(), con)

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Flag", PrimitiveKind.INT)
    override fun deserialize(decoder: Decoder): T {
        val int = decoder.decodeInt()
        if (int == 0) return con(emptyList())
        return con(values.filter { (1 shl it.ordinal) and int != 0 })
    }

    override fun serialize(encoder: Encoder, value: T) {
        var int = 0
        value.forEach { int = 1 shl it.ordinal or int }
        encoder.encodeInt(int)
    }
}
