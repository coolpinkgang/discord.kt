package io.github.romangraef.discordkt.models.serial

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

interface IDEnum {
    val id: Int
}

abstract class IDBasedSerializer<T : IDEnum>(values: Array<T>) : KSerializer<T> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("IDBasedSerializer", PrimitiveKind.INT)
    private val bufferedValues = arrayOfNulls<IDEnum>(values.map { it.id }.maxOrNull()!!).also {
        values.forEach { value -> it[value.id] = value }
    }

    @Suppress("UNCHECKED_CAST")
    override fun deserialize(decoder: Decoder): T =
        bufferedValues[decoder.decodeInt()] as T


    override fun serialize(encoder: Encoder, value: T) {
        encoder.encodeInt(value.id)
    }
}
