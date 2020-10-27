package dev.discordkt.models.gateway

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Resume(
    val token: String,
    @SerialName("session_id")
    val sessionId: String,
    @SerialName("seq")
    val sequence: Int
)
