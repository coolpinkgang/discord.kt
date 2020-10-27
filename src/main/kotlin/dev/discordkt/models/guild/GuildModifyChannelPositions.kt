package dev.discordkt.models.guild

import dev.discordkt.snowflake.Snowflake

import kotlinx.serialization.Serializable

@Serializable
data class GuildModifyChannelPositions(
    val id: Snowflake,
    val position: Int
)
