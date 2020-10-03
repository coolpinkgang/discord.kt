package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.models.serial.OrdinalSerializer

import kotlinx.serialization.Serializable

@Serializable(with = VerificationLevel.Serializer::class)
enum class VerificationLevel {
    NONE,
    LOW,
    MEDIUM,
    HIGH,
    VERY_HIGH;
    class Serializer : OrdinalSerializer<VerificationLevel>(values())
}
