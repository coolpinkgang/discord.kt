package io.github.romangraef.discordkt.models.channel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmbedImage(
    val url: String? = null,
    @SerialName("proxy_url")
    val proxyUrl: String? = null,
    val height: Int? = null,
    val width: Int? = null
)
