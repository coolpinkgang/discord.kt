package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.models.serial.Snowflake

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildWidgetStructure(
    val enabled: Boolean,
    @SerialName("channel_id")
    val channelId: Snowflake?
)