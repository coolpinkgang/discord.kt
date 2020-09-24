package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.serial.Snowflake

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class VoiceState(
    @SerialName("guild_id")
    val guildId: Snowflake,
    @SerialName("channel_id")
    val channelId: Snowflake,
    @SerialName("user_id")
    val userId: Snowflake,
    val member: Member? = null,
    @SerialName("session_id")
    val sessionId: Snowflake,
    val deaf: Boolean,
    val mute: Boolean,
    @SerialName("self_deaf")
    val selfDeaf: Boolean,
    @SerialName("self_mute")
    val selfMute: Boolean,
    @SerialName("self_stream")
    val selfStream: Boolean = false,
    @SerialName("self_video")
    val selfVideo: Boolean = false,
    val suppress: Boolean
)
