package dev.discordkt.snowflake

import java.time.Instant
import java.time.OffsetDateTime
import java.time.ZoneOffset

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
    fun equalsSnowflake(other: Any?) = (other as? BaseSnowflake)?.longId == longId
    fun hashSnowflake() = longId.hashCode()
}
