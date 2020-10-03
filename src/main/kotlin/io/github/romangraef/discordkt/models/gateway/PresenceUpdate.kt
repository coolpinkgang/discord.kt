package io.github.romangraef.discordkt.models.gateway

import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.user.User
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
