package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.models.serial.FlagsSerializer
import kotlinx.serialization.Serializable

enum class MessageFlag {
    CROSSPOSTED,
    IS_CROSSPOST,
    SUPPRESS_EMBEDS,
    SOURCE_MESSAGE_DELETED,
    URGENT;
    @Serializable(with = MessageFlag.BitField.Serializer::class)
    data class BitField(val backingList: List<MessageFlag>) : List<MessageFlag> by backingList {
        class Serializer : FlagsSerializer<MessageFlag, BitField>(::values, ::BitField)
    }
}
