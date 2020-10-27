package dev.discordkt.models.emoji

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.models.serial.ImageData

import kotlinx.serialization.Serializable

@Serializable
data class CreateGuildEmoji(
    val name: String,
    val image: ImageData,
    val roles: List<Snowflake>
)
