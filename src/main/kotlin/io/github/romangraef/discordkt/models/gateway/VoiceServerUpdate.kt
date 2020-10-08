package io.github.romangraef.discordkt.models.gateway

import io.github.romangraef.discordkt.snowflake.Snowflake

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoiceServerUpdate(
    val token: String,
    @SerialName("guild_id")
    val guildId: Snowflake,
    val endpoint: String
)
