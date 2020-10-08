package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.snowflake.Snowflake
import io.github.romangraef.discordkt.models.channel.ChannelType
import io.github.romangraef.discordkt.models.channel.Overwrite

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildCreateChannel(
    val name: String,
    val type: ChannelType,
    val topic: String,
    val bitrate: Int,
    @SerialName("user_limit")
    val userLimit: Int,
    @SerialName("rate_limit_per_user")
    val rateLimitPerUser: Int,
    val position: Int,
    @SerialName("permission_overwrites")
    val permissionOverwrites: List<Overwrite>,
    @SerialName("parent_id")
    val parentId: Snowflake,
    val nsfw: Boolean
)
