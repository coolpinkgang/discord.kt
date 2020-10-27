package dev.discordkt.models.gateway

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.models.user.User

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PresenceUpdate(
    val user: User,
    @SerialName("guild_id")
    val guildId: Snowflake,
    val status: Status,
    val activities: List<Activity>,
    @SerialName("client_status")
    val clientStatus: ClientStatus
)
