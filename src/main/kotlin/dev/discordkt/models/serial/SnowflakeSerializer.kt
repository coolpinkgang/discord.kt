package dev.discordkt.models.serial

import dev.discordkt.snowflake.Snowflake

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

class SnowflakeSerializer : KSerializer<Snowflake> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Snowflake", PrimitiveKind.STRING)
    override fun deserialize(decoder: Decoder): Snowflake = Snowflake.of(decoder.decodeString())
    override fun serialize(encoder: Encoder, value: Snowflake) = encoder.encodeString(value.stringId)
}
