package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.snowflake.Snowflake
import io.github.romangraef.discordkt.snowflake.SnowflakeMixin

import kotlinx.serialization.Serializable

@Serializable
data class IntegrationAccount(
    override val id: Snowflake,
    val name: String
) : SnowflakeMixin
