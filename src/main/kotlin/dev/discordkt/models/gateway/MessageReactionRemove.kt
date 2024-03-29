package dev.discordkt.models.gateway

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.models.emoji.Emoji

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageReactionRemove(
    @SerialName("user_id")
    val userId: Snowflake,
    @SerialName("channel_id")
    val channelId: Snowflake,
    @SerialName("message_id")
    val messageId: Snowflake,
    @SerialName("guild_id")
    val guildId: Snowflake?,
    val emoji: Emoji
)
