package dev.discordkt.models.webhook

import dev.discordkt.models.serial.ImageData
import dev.discordkt.snowflake.Snowflake

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WebhookModify(
    val name: String,
    val avatar: ImageData?,
    @SerialName("channel_id")
    val channelId: Snowflake
)
