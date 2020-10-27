package dev.discordkt.models.gateway

import kotlinx.serialization.Serializable

@Serializable
data class GatewayStatusUpdate(
    val since: Int?,
    val activities: List<Activity>?,
    val status: Status,
    val afk: Boolean
)
