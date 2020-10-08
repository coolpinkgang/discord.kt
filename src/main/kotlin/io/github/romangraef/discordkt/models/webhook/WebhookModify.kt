package io.github.romangraef.discordkt.models.webhook

import io.github.romangraef.discordkt.models.serial.ImageData
import io.github.romangraef.discordkt.snowflake.Snowflake

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WebhookModify(
    val name: String,
    val avatar: ImageData?,
    @SerialName("channel_id")
    val channelId: Snowflake
)
