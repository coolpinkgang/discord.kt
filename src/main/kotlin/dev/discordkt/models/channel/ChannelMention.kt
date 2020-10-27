package dev.discordkt.models.channel

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.snowflake.SnowflakeMixin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChannelMention(
    override val id: Snowflake,
    @SerialName("guild_id")
    val guildId: Snowflake,
    val type: ChannelMentionType,
    val name: String
): SnowflakeMixin
