package io.github.romangraef.discordkt.models.channel

import kotlinx.serialization.Serializable

@Serializable
data class EmbedVideo(
    val url: String? = null,
    val height: Int? = null,
    val width: Int? = null
)
