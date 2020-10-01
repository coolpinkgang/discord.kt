package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.models.serial.Snowflake

import kotlinx.serialization.Serializable
@Serializable
data class Overwrite(
    val id: Snowflake,
    val type: OverwriteType,
    val allow: String,
    val deny: String,
)
