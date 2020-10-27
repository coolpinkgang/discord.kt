package dev.discordkt.models.guild

import dev.discordkt.snowflake.Snowflake

import kotlinx.serialization.Serializable

@Serializable
data class UnavailableGuild(
    val id: Snowflake,
    val unavailable: Boolean
)
