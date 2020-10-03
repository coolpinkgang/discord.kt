package io.github.romangraef.discordkt.models.gateway

import kotlinx.serialization.Serializable

@Serializable
data class ActivitySecrets(
    val join: String? = null,
    val spectate: String? = null,
    val match: String? = null
)
