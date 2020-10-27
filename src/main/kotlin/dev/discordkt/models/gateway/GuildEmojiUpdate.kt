package dev.discordkt.models.gateway

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.models.emoji.Emoji

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildEmojiUpdate(
    @SerialName("guild_id")
    val guildId: Snowflake,
    val emojis: List<Emoji>
)
