package io.github.romangraef.discordkt.models.gateway

import kotlinx.serialization.Serializable

@Serializable
data class ActivityParty (
    val id: String? = null,
    val size: List<Int>? = null
)
