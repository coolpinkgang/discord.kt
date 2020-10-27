package dev.discordkt.models.gateway

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.models.user.User

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildBanAdd(
    @SerialName("guild_id")
    val guildId: Snowflake,
    val user: User
)
