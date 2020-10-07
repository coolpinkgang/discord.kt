package io.github.romangraef.discordkt.models.user

import io.github.romangraef.discordkt.models.serial.Snowflake
import kotlinx.serialization.Serializable

@Serializable
data class DMCreate(
    val recipientId: Snowflake
)
