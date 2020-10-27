package dev.discordkt.models.user

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.snowflake.SnowflakeMixin
import dev.discordkt.models.guild.Integration

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
    ) : SnowflakeMixin
