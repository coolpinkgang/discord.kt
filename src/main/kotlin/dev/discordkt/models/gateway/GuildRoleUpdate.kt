package dev.discordkt.models.gateway

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.models.permissions.Role

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildRoleUpdate(
    @SerialName("guild_id")
    val guildId: Snowflake,
    val role: Role
)
