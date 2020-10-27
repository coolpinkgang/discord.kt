package dev.discordkt.models.channel

import dev.discordkt.models.serial.FlagsSerializer
import kotlinx.serialization.Serializable

enum class MessageFlag {
    CROSSPOSTED,
    IS_CROSSPOST,
    SUPPRESS_EMBEDS,
    SOURCE_MESSAGE_DELETED,
    URGENT;
    @Serializable(with = BitField.Serializer::class)
    data class BitField(val backingList: List<MessageFlag>) : List<MessageFlag> by backingList {
        class Serializer : FlagsSerializer<MessageFlag, BitField>(::values, ::BitField)
    }
}
