package io.github.romangraef.discordkt.models.user

import io.github.romangraef.discordkt.models.guild.Integration
import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.serial.SnowflakeMixin
import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable

@Serializable
data class Connection(
    override val id: Snowflake,
    val name: String,
    val type: String,
    val revoked: Boolean? = null,
    val integrations: List<Integration>? = null,
    val verified: Boolean,
    @SerialName("friend_sync")
    val friendSync: Boolean,
    @SerialName("show_activity")
    val showActivity: Boolean,
    val visibility: Visibility
    ) : SnowflakeMixin()
