package io.github.romangraef.discordkt.models.gateway

import io.github.romangraef.discordkt.models.serial.Snowflake

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildRoleDelete(
    @SerialName("guild_id")
    val guildId: Snowflake,
    @SerialName("role_id")
    val roleId: Snowflake
)
