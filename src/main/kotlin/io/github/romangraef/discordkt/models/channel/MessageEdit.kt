package io.github.romangraef.discordkt.models.channel

import kotlinx.serialization.Serializable

@Serializable
data class MessageEdit(
    val content: String?,
    val embed: Embed?,
    val flags: MessageFlag.BitField?
)
