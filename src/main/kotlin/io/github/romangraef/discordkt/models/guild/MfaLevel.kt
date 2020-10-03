package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.models.serial.OrdinalSerializer
import kotlinx.serialization.Serializable

@Serializable(with = MfaLevel.Serializer::class)
enum class MfaLevel {
    NONE,
    ELEVATED;
    class Serializer : OrdinalSerializer<MfaLevel>(values())
}
