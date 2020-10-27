package dev.discordkt.models.channel

import dev.discordkt.snowflake.Snowflake

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChannelUpdate(
    val name: String,
    val type: ChannelType,
    val position: Int? = null,
    val topic: String? = null,
    val nsfw: Boolean? = null,
    @SerialName("rate_limit_per_user")
    val rateLimitPerUser: Int? = null,
    val bitrate: Int? = null,
    @SerialName("user_limit")
    val userLimit: Int? = null,
    @SerialName("permission_overwrites")
    val permissionOverwrite: Overwrite? = null,
    @SerialName("parentId")
    val parentId: Snowflake? = null
)
