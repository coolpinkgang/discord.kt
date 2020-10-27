package dev.discordkt.models

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = Color.Serializer::class)
class Color(val red: Byte, val green: Byte, val blue: Byte) {
    fun toInt(): Int = red.toInt() shl 8 + green.toInt() shl 8 + blue.toInt()
    companion object {
        val BLACK = Color(0, 0, 0)
        fun of(rgb: Int) =
            Color((rgb shr 16 and 0xFF).toByte(), (rgb shr 8 and 0xFF).toByte(), (rgb and 0xFF).toByte())
    }
    class Serializer : KSerializer<Color> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("color", PrimitiveKind.INT)
        override fun deserialize(decoder: Decoder): Color = of(decoder.decodeInt())
        override fun serialize(encoder: Encoder, value: Color) = encoder.encodeInt(value.toInt())
    }
}
