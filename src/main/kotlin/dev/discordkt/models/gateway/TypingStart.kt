package dev.discordkt.models.gateway

import dev.discordkt.snowflake.Snowflake

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TypingStart(
    @SerialName("channel_id")
    val channelId: Snowflake,
    @SerialName("guild_id")
    val guildId: Snowflake,
    @SerialName("user_id")
    val userId: Snowflake,
    val timestamp: Int
)
