package dev.discordkt.models.channel

import dev.discordkt.models.emoji.Emoji

import kotlinx.serialization.Serializable

@Serializable
data class Reaction(
    val count: Int,
    val me: Boolean,
    val emoji: Emoji
)
