package dev.discordkt.models.webhook

import dev.discordkt.models.serial.ImageData
import kotlinx.serialization.Serializable

@Serializable
data class WebhookCreate(
    val name: String,
    val avatar: ImageData?
)
