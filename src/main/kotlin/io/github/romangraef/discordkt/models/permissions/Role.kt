package io.github.romangraef.discordkt.models.permissions

import io.github.romangraef.discordkt.snowflake.Snowflake
import io.github.romangraef.discordkt.snowflake.SnowflakeMixin

import kotlinx.serialization.Serializable

@Serializable
data class Role(
    override val id: Snowflake,
    val name: String,
) : SnowflakeMixin
