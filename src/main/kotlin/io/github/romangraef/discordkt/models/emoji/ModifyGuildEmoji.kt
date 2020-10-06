package io.github.romangraef.discordkt.models.emoji

import io.github.romangraef.discordkt.models.serial.Snowflake
import kotlinx.serialization.Serializable

@Serializable
data class ModifyGuildEmoji(
    val name: String,
    val roles: List<Snowflake>? = null
)
