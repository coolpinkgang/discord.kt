package io.github.romangraef.discordkt.models.user

import io.github.romangraef.discordkt.models.serial.OrdinalSerializer
import kotlinx.serialization.Serializable

@Serializable(with = Visibility.Serializer::class)
enum class Visibility {
    NONE,
    EVERYONE;
    class Serializer : OrdinalSerializer<Visibility>(values())
}
