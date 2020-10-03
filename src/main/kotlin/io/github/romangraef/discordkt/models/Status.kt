package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.serial.EnumNameSerializer
import kotlinx.serialization.Serializable

@Serializable
enum class Status {
    IDLE,
    DND,
    ONLINE,
    OFFLINE;
    class Serializer : EnumNameSerializer<Status>(::valueOf, false)
}
