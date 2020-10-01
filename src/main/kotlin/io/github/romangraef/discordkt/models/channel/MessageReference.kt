package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.models.serial.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageReference(
    @SerialName("message_id")
    val messageId: Snowflake? = null,
    @SerialName("channel_id")
    val channelId: Snowflake,
    @SerialName("guild_id")
    val guildId: Snowflake? = null
)
