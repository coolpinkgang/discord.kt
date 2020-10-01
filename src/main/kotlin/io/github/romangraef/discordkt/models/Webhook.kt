package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.serial.SnowflakeMixin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Webhook(
    override val id: Snowflake,
    @SerialName("type")
    val type: Type,
    @SerialName("guild_id")
    val guildId: Snowflake? = null,
    @SerialName("channel_id")
    val channelId: Snowflake,
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
