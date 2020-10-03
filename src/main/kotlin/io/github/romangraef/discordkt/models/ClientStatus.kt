package io.github.romangraef.discordkt.models

import kotlinx.serialization.Serializable

@Serializable
data class ClientStatus(
    val desktop: Status?,
    val mobile: Status?,
    val web: Status?
)
