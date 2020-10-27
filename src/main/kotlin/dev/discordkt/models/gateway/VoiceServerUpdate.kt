package dev.discordkt.models.gateway

import dev.discordkt.snowflake.Snowflake

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoiceServerUpdate(
    val token: String,
    @SerialName("guild_id")
    val guildId: Snowflake,
    val endpoint: String
)
