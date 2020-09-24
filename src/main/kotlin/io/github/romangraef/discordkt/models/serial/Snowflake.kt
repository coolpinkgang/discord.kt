package io.github.romangraef.discordkt.models.serial

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable(with = Snowflake.Serializer::class)
data class Snowflake(
    override val stringId: String
) : BaseSnowflake {
    override val longId = stringId.toLong()
    override val asSnowflake: Snowflake
        get() = this
    class Serializer : KSerializer<Snowflake> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Snowflake", PrimitiveKind.STRING)
        override fun deserialize(decoder: Decoder): Snowflake = Snowflake(decoder.decodeString())
        override fun serialize(encoder: Encoder, value: Snowflake) = encoder.encodeString(value.stringId)
    }
}

interface BaseSnowflake : Comparable<BaseSnowflake> {
    val asSnowflake: Snowflake
    override fun compareTo(other: BaseSnowflake): Int =
        longId.compareTo(other.longId)

    val stringId: String
    val longId: Long
}

abstract class SnowflakeMixin : BaseSnowflake {
    abstract val id: Snowflake
    override val longId: Long get() = id.longId
    override val stringId: String get() = id.stringId
    override val asSnowflake: Snowflake
        get() = id
}
