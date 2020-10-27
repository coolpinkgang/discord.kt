package dev.discordkt.models.user

import dev.discordkt.models.serial.OrdinalSerializer

import kotlinx.serialization.Serializable

@Serializable(with= PremiumType.Serializer::class)
enum class PremiumType {
    NONE, NITRO_CLASSIC, NITRO;
    class Serializer : OrdinalSerializer<PremiumType>(values())
}
