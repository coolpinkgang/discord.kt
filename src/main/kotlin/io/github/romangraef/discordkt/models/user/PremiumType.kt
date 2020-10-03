package io.github.romangraef.discordkt.models.user

import io.github.romangraef.discordkt.models.serial.OrdinalSerializer
import kotlinx.serialization.Serializable

@Serializable(with= PremiumType.Serializer::class)
enum class PremiumType {
    NONE, NITRO_CLASSIC, NITRO;
    class Serializer : OrdinalSerializer<PremiumType>(values())
}
