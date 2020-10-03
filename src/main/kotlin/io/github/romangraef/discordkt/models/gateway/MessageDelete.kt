package io.github.romangraef.discordkt.models.gateway

import io.github.romangraef.discordkt.models.guild.Guild
import io.github.romangraef.discordkt.models.serial.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageDelete(
    val id: Snowflake,
    @SerialName("channel_id")
    val channelId: Snowflake,
    @SerialName("guild_id")
    val guildId: Guild? = null
)
