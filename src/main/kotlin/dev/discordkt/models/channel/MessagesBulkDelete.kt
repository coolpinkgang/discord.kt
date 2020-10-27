package dev.discordkt.models.channel

import dev.discordkt.snowflake.Snowflake

import kotlinx.serialization.Serializable

@Serializable
data class MessagesBulkDelete(
    val messages: List<Snowflake>
)
