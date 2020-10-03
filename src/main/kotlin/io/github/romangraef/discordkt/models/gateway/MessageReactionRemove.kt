package io.github.romangraef.discordkt.models.gateway

import io.github.romangraef.discordkt.models.emoji.Emoji
import io.github.romangraef.discordkt.models.guild.GuildMember
import io.github.romangraef.discordkt.models.serial.Snowflake

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
