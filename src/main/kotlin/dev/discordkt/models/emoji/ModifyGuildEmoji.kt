package dev.discordkt.models.emoji

import dev.discordkt.snowflake.Snowflake

import kotlinx.serialization.Serializable

@Serializable
data class ModifyGuildEmoji(
    val name: String,
    val roles: List<Snowflake>? = null
)
