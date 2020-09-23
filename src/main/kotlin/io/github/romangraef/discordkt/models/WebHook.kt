package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.serial.SnowflakeMixin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WebHook(
    override val id: Snowflake,
    @SerialName("type") val intType: Int,
    @SerialName("guild_id") val stringGuildID: String? = null,
    @SerialName("channel_id") val stringChannelID: String,
    val user: User? = null,
    val name: String?,
    val avatar: String?,
    val token: String? = null
) : SnowflakeMixin() {
    enum class Type {
        INCOMING,
        CHANNEL_FOLLOWER
    }
}
