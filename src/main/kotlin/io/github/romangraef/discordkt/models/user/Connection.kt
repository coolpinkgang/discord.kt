package io.github.romangraef.discordkt.models.user

import io.github.romangraef.discordkt.models.guild.Integration
import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.serial.SnowflakeMixin

import kotlinx.serialization.Serializable

@Serializable
data class Connection(
    override val id: Snowflake,
    val name: String,
    val type: String,
    val revoked: Boolean? = null,
    val integrations: List<Integration>? = null,
    val verified: Boolean,
    val friendSync: Boolean,
    val showActivity: Boolean,
    val visibility: Visibility
    ) : SnowflakeMixin()
