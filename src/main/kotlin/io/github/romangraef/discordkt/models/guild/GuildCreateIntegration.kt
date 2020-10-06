package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.models.serial.Snowflake
import kotlinx.serialization.Serializable

@Serializable
data class GuildCreateIntegration(
    val type: String,
    val id: Snowflake
)
