package dev.discordkt.models.gateway

import dev.discordkt.snowflake.Snowflake

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GatewayUpdateVoiceState(
    @SerialName("guild_id")
    val guildId: Snowflake,
    @SerialName("channel_id")
    val channelId: Snowflake?,
    @SerialName("self_mute")
    val selfMute: Boolean,
    @SerialName("self_deaf")
    val selfDeaf: Boolean
)
