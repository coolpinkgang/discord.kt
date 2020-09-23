package io.github.romangraef.discordkt.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WebHook(
    @SerialName("id") val stringID: String,
    @SerialName("type") val intType: Int,
    @SerialName("guild_id") val stringGuildID: String? = null,
    @SerialName("channel_id") val stringChannelID: String,
    val user: User? = null,
    val name: String?,
    val avatar: String?,
    val token: String? = null
) {
    enum class Type {
        INCOMING,
        CHANNEL_FOLLOWER
    }
}
