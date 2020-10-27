package dev.discordkt.models.user

import dev.discordkt.snowflake.Snowflake

import kotlinx.serialization.Serializable

@Serializable
data class DMCreate(
    val recipientId: Snowflake
)
