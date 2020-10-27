package dev.discordkt.models.permissions

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.snowflake.SnowflakeMixin

import kotlinx.serialization.Serializable

@Serializable
data class Role(
    override val id: Snowflake,
    val name: String,
) : SnowflakeMixin
