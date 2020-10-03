package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.models.User
import kotlinx.serialization.Serializable

@Serializable
data class Ban(
    val reason: String?,
    val user: User
)
