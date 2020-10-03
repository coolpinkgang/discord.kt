package io.github.romangraef.discordkt.models.gateway

import io.github.romangraef.discordkt.models.serial.FlagsSerializer
import kotlinx.serialization.Serializable

enum class ActivityFlag {
    INSTANCE,
    JOIN,
    SPECTATE,
    JOIN_REQUEST,
    SYNC,
    PLAY;
    @Serializable(with = BitField.Serializer::class)
    class BitField(val backingList: List<ActivityFlag>) : List<ActivityFlag> by backingList {
        class Serializer : FlagsSerializer<ActivityFlag, BitField>(::values, ::BitField)
    }
}
