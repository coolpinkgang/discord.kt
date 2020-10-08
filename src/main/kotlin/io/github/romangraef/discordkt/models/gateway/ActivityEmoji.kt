package io.github.romangraef.discordkt.models.gateway

import io.github.romangraef.discordkt.snowflake.Snowflake

import kotlinx.serialization.Serializable

@Serializable
data class ActivityEmoji(
    val name: String,
    val id: Snowflake? = null,
    val animated: Boolean? = null
)
