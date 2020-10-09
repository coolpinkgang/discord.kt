package io.github.romangraef.discordkt.snowflake

import io.github.romangraef.discordkt.models.serial.SnowflakeSerializer
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime

@Serializable(with = SnowflakeSerializer::class)
class Snowflake private constructor(
    override val longId: Long
) : BaseSnowflake {
    override val stringId get() = longId.toString()
    override val asSnowflake get() = this

    override fun equals(other: Any?): Boolean = equalsSnowflake(other)
    override fun hashCode(): Int = hashSnowflake()
    override fun toString(): String = stringId

    companion object {
        fun of(offset: OffsetDateTime) = of(offset, 0, 0, 0)

        fun of(offset: OffsetDateTime, workerId: Long, processId: Long, incrementId: Long) =
            of(offset.toInstant().toEpochMilli() - 1420070400000, workerId, processId, incrementId)

        fun of(discordEpoch: Long, workerId: Long, processId: Long, incrementId: Long) =
            of((discordEpoch shl 22) or (workerId shl 17) or (processId shl 12) or incrementId)

        fun of(longId: Long) = Snowflake(longId)
        fun of(stringId: String) = Snowflake(stringId.toLong())
    }
}
