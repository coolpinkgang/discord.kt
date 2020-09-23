package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.serial.SnowflakeMixin
import kotlinx.serialization.Serializable

@Serializable
data class Account(
    override val id: Snowflake,
    val name: String
) : SnowflakeMixin()
