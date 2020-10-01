package io.github.romangraef.discordkt.models.channel

import kotlinx.serialization.Serializable

@Serializable
enum class MessageFlags {
    CROSSPOSTED,
    IS_CROSSPOST,
    SUPPRESS_EMBEDS,
    SOURCE_MESSAGE_DELETED,
    URGENT
}
