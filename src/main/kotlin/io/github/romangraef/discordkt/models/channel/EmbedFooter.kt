package io.github.romangraef.discordkt.models.channel

import kotlinx.serialization.Serializable

@Serializable
data class EmbedFooter(
    val text: String,
    val iconUrl: String? = null,
    val proxyIconUrl: String? = null
)
