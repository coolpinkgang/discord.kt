package dev.discordkt.models.guild

import dev.discordkt.models.serial.OrdinalSerializer

import kotlinx.serialization.Serializable

@Serializable(with = MfaLevel.Serializer::class)
enum class MfaLevel {
    NONE,
    ELEVATED;
    class Serializer : OrdinalSerializer<MfaLevel>(values())
}
