package dev.discordkt.models.guild

import dev.discordkt.snowflake.Snowflake

import kotlinx.serialization.Serializable

@Serializable
data class GuildCreateIntegration(
    val type: String,
    val id: Snowflake
)
