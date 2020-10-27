package dev.discordkt.models.channel

import dev.discordkt.snowflake.Snowflake

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FollowNewsChannel(
    @SerialName("webhook_channel_id")
    val webhookChannelId: Snowflake
)
