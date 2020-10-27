package dev.discordkt.models.channel

import kotlinx.serialization.Serializable

@Serializable
data class MessageActivity(
    val type: MessageActivityType,
    val partyId: String? = null
)
