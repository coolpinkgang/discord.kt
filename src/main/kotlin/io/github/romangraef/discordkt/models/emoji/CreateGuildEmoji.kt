package io.github.romangraef.discordkt.models.emoji

import io.github.romangraef.discordkt.models.serial.ImageData
import io.github.romangraef.discordkt.models.serial.Snowflake

import kotlinx.serialization.Serializable

@Serializable
data class CreateGuildEmoji(
    val name: String,
    val image: ImageData,
    val roles: List<Snowflake>
)
