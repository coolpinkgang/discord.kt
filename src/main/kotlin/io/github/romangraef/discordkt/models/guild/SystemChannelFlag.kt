package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.models.serial.FlagsSerializer
import kotlinx.serialization.Serializable

enum class SystemChannelFlag {
    SUPPRESS_JOIN_NOTIFICATIONS,
    SUPPRESS_PREMIUM_SUBSCRIPTIONS;
    @Serializable(with = BitField.Serializer::class)
    class BitField(val backingList: List<SystemChannelFlag>) : List<SystemChannelFlag> by backingList {
        class Serializer : FlagsSerializer<SystemChannelFlag, BitField>(SystemChannelFlag::values, ::BitField)
    }
}
