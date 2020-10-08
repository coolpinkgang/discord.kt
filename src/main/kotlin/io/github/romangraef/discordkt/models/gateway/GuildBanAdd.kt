package io.github.romangraef.discordkt.models.gateway

import io.github.romangraef.discordkt.snowflake.Snowflake
import io.github.romangraef.discordkt.models.user.User

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildBanAdd(
    @SerialName("guild_id")
    val guildId: Snowflake,
    val user: User
)
