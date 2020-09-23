package io.github.romangraef.discordkt.models.serial

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = ForSnowflake::class)
data class Snowflake(
    val stringId: String
) : BaseSnowflake {
    val longId = stringId.toLong()
    override val asSnowflake: Snowflake
        get() = this
}

class ForSnowflake : KSerializer<Snowflake> {
    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Snowflake", PrimitiveKind.STRING)

    override fun deserialize(decoder: Decoder): Snowflake =
        Snowflake(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: Snowflake) {
        encoder.encodeString(value.stringId)
    }
}

interface BaseSnowflake : Comparable<BaseSnowflake> {
    val asSnowflake: Snowflake
    override fun compareTo(other: BaseSnowflake): Int =
        asSnowflake.longId.compareTo(other.asSnowflake.longId)

}

abstract class SnowflakeMixin : BaseSnowflake {
    abstract val id: Snowflake
    override val asSnowflake: Snowflake
        get() = id
}
