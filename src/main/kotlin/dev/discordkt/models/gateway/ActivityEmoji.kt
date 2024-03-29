package dev.discordkt.models.gateway

import dev.discordkt.snowflake.Snowflake

import kotlinx.serialization.Serializable

@Serializable
data class ActivityEmoji(
    val name: String,
    val id: Snowflake? = null,
    val animated: Boolean? = null
)
