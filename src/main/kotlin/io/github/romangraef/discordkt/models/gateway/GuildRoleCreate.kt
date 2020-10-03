package io.github.romangraef.discordkt.models.gateway

import io.github.romangraef.discordkt.models.permissions.Role
import io.github.romangraef.discordkt.models.serial.Snowflake

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildRoleCreate(
    @SerialName("guild_id")
    val guildId: Snowflake,
    val role: Role
)
