package io.github.romangraef.discordkt.models.guild

import kotlinx.serialization.Serializable

@Serializable
data class ModifyCurrentUserNick(
    val nick: String? = null
)
