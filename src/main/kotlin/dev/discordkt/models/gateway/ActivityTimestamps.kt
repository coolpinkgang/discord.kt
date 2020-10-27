package dev.discordkt.models.gateway

import kotlinx.serialization.Serializable

@Serializable
data class ActivityTimestamps(
    val start: Int? = null,
    val end: Int? = null
)
