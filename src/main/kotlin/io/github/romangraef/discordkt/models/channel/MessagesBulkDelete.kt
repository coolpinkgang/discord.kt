package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.models.serial.Snowflake
import kotlinx.serialization.Serializable

@Serializable
data class MessagesBulkDelete(
    val messages: List<Snowflake>
)