package io.github.romangraef.discordkt.models.serial

import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset
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

    override fun equals(other: Any?): Boolean = other != null && other == longId
    override fun hashCode(): Int = longId.hashCode()
    override fun toString(): String = stringId

    class Serializer : KSerializer<Snowflake> {
        override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Snowflake", PrimitiveKind.STRING)
        override fun deserialize(decoder: Decoder): Snowflake = Snowflake(decoder.decodeString())
        override fun serialize(encoder: Encoder, value: Snowflake) = encoder.encodeString(value.stringId)
    }

    companion object {
        fun of(offset: OffsetDateTime) = of(offset, 0, 0, 0)

        fun of(offset: OffsetDateTime, workerId: Long, processId: Long, incrementId: Long) =
            of(offset.toInstant().toEpochMilli() - 1420070400000, workerId, processId, incrementId)

        fun of(discordEpoch: Long, workerId: Long, processId: Long, incrementId: Long) =
            of((discordEpoch shl 22) or (workerId shl 17) or (processId shl 12) or incrementId)

        fun of(longId: Long) = Snowflake(longId.toString())
    }
}

interface BaseSnowflake : Comparable<BaseSnowflake> {
    val asSnowflake: Snowflake
    override fun compareTo(other: BaseSnowflake): Int =
        longId.compareTo(other.longId)

    val asOffsetDateTime
        get() = OffsetDateTime.ofInstant(Instant.ofEpochMilli((longId shr 22) + 1420070400000), ZoneOffset.UTC)
    val createdAt get() = asOffsetDateTime
    val workerId
        get() = longId and 0x3E0000 shr 17
    val processId
        get() = longId and 0x1F000 shr 12
    val incrementId
        get() = longId and 0xFFF
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
