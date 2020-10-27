package dev.discordkt.models.guild

import dev.discordkt.models.serial.FlagsSerializer

import kotlinx.serialization.Serializable

enum class SystemChannelFlag {
    SUPPRESS_JOIN_NOTIFICATIONS,
    SUPPRESS_PREMIUM_SUBSCRIPTIONS;
    @Serializable(with = BitField.Serializer::class)
    data class BitField(val backingList: List<SystemChannelFlag>) : List<SystemChannelFlag> by backingList {
        class Serializer : FlagsSerializer<SystemChannelFlag, BitField>(SystemChannelFlag::values, ::BitField)
    }
}
