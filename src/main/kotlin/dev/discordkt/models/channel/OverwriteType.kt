package dev.discordkt.models.channel

import dev.discordkt.models.serial.EnumNameSerializer

import kotlinx.serialization.Serializable

@Serializable(OverwriteType.Serializer::class)
enum class OverwriteType {
    ROLE,
    MEMBER;
    class Serializer : EnumNameSerializer<OverwriteType>(OverwriteType::valueOf, false)
}
