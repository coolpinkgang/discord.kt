package io.github.romangraef.discordkt.models.gateway

import io.github.romangraef.discordkt.models.serial.Snowflake

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildMemberAddExtra(
    @SerialName("guild_id")
    val guildId: Snowflake
)
