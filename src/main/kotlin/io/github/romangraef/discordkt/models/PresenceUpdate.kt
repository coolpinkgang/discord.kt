package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.user.User
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PresenceUpdate(
        val user: User,
        val roles: List<Snowflake>,
        val game: Activity?,
        @SerialName("guild_id")
    val guildId: Snowflake,
        val status: Status,
        val activities: List<Activity>,
        @SerialName("client_status")
    val clientStatus: ClientStatus,
        @SerialName("premium_since")
    val premiumSince: String,
        val nick: String? = null,
) {
    @Serializable
    data class ClientStatus(
        val desktop: Status?,
        val mobile: Status?,
        val web: Status?
    )
    enum class Status {
        idle, dnd, online, offline
    }
}
