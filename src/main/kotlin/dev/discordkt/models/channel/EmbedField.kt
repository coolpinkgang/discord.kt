package dev.discordkt.models.channel

import kotlinx.serialization.Serializable

@Serializable
data class EmbedField(
    val name: String,
    val value: String,
    val inline: Boolean? = null
)
