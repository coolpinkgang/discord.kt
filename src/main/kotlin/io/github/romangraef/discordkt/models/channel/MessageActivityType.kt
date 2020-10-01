package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.models.serial.OrdinalSerializer
import kotlinx.serialization.Serializable

@Serializable(with = MessageActivityType.Serializer::class)
enum class MessageActivityType {
    JOIN,
    SPECTATE,
    LISTEN,
    JOIN_REQUEST;
    class Serializer : OrdinalSerializer<MessageActivityType>(values())
}
