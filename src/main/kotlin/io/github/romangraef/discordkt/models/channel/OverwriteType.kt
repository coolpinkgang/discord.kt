package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.models.serial.EnumNameSerializer

import kotlinx.serialization.Serializable

@Serializable(OverwriteType.Serializer::class)
enum class OverwriteType {
    ROLE,
    MEMBER;
    class Serializer : EnumNameSerializer<OverwriteType>(OverwriteType::valueOf, false)
}
