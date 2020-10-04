package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.models.serial.Snowflake

import kotlinx.serialization.Serializable

@Serializable
data class UnavailableGuild(
    val id: Snowflake,
    val unavailable: Boolean
)