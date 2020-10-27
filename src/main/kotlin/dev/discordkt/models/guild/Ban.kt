package dev.discordkt.models.guild

import dev.discordkt.models.user.User

import kotlinx.serialization.Serializable

@Serializable
data class Ban(
    val reason: String?,
    val user: User
)
