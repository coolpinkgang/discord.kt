@file:UseSerializers(ISO8601Serializer::class)
package dev.discordkt.models.gateway

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.models.serial.ISO8601Serializer

import java.time.OffsetDateTime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

@Serializable
data class ChannelPinsUpdate(
    @SerialName("guild_id")
    val guildId: Snowflake? = null,
    @SerialName("channel_id")
    val channelId: Snowflake,
    @SerialName("last_pin_timestamp")
    val lastPinTimestamp: OffsetDateTime
)
