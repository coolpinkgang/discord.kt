package io.github.romangraef.discordkt.models.webhook

import io.github.romangraef.discordkt.models.serial.ImageData
import kotlinx.serialization.Serializable

@Serializable
data class WebhookCreate(
    val name: String,
    val avatar: ImageData?
)
