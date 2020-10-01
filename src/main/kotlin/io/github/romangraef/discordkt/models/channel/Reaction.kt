package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.models.emoji.Emoji
import kotlinx.serialization.Serializable

@Serializable
data class Reaction(
    val count: Int,
    val me: Boolean,
    val emoji: Emoji
)
