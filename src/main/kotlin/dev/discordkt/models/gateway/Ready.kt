package dev.discordkt.models.gateway

import dev.discordkt.models.guild.UnavailableGuild
import dev.discordkt.models.user.User

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ready(
    @SerialName("v")
    val version: Int,
    val user: User,
    @SerialName("private_channels")
    val privateChannels: List<Unit> = emptyList(),
    val guilds: List<UnavailableGuild>,
    @SerialName("session_id")
    val sessionId: String,
    val shard: Pair<Int, Int>? = null
)
