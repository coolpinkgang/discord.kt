package dev.discordkt.models.channel

import kotlinx.serialization.Serializable

@Serializable
data class EmbedAuthor(
    val name: String? = null,
    val url: String? = null
)
