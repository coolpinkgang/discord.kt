@file:UseSerializers(ISO8601Serializer::class)
package io.github.romangraef.discordkt.models.gateway

import io.github.romangraef.discordkt.models.serial.ISO8601Serializer
import io.github.romangraef.discordkt.models.serial.Snowflake

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
