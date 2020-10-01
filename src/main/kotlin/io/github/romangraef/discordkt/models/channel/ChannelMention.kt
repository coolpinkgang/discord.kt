package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.serial.SnowflakeMixin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChannelMention(
    override val id: Snowflake,
    @SerialName("guild_id")
    val guildId: Snowflake,
    val type: ChannelMentionType,
    val name: String
): SnowflakeMixin()
