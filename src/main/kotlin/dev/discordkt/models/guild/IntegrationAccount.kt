package dev.discordkt.models.guild

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.snowflake.SnowflakeMixin

import kotlinx.serialization.Serializable

@Serializable
data class IntegrationAccount(
    override val id: Snowflake,
    val name: String
) : SnowflakeMixin
