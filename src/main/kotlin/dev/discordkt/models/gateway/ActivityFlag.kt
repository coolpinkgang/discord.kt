package dev.discordkt.models.gateway

import dev.discordkt.models.serial.FlagsSerializer
import kotlinx.serialization.Serializable

enum class ActivityFlag {
    INSTANCE,
    JOIN,
    SPECTATE,
    JOIN_REQUEST,
    SYNC,
    PLAY;
    @Serializable(with = BitField.Serializer::class)
    data class BitField(val backingList: List<ActivityFlag>) : List<ActivityFlag> by backingList {
        class Serializer : FlagsSerializer<ActivityFlag, BitField>(::values, ::BitField)
    }
}
