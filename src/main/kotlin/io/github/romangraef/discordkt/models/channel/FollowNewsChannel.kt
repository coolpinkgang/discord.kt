package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.models.serial.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FollowNewsChannel(
    @SerialName("webhook_channel_id")
    val webhookChannelId: Snowflake
)
