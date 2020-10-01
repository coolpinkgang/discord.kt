package io.github.romangraef.discordkt.models.channel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class EmbedThumbnail(
    val url: String? = null,
    @SerialName("url_proxy")
    val urlProxy: String? = null,
    val height: Int? = null,
    val width: Int? = null
)
