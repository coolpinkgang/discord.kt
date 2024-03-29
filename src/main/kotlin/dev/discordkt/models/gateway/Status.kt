package dev.discordkt.models.gateway

import dev.discordkt.models.serial.EnumNameSerializer

import kotlinx.serialization.Serializable

@Serializable
enum class Status {
    IDLE,
    DND,
    ONLINE,
    OFFLINE;
    class Serializer : EnumNameSerializer<Status>(::valueOf, false)
}
