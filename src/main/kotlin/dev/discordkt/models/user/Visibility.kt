package dev.discordkt.models.user

import dev.discordkt.models.serial.OrdinalSerializer

import kotlinx.serialization.Serializable

@Serializable(with = Visibility.Serializer::class)
enum class Visibility {
    NONE,
    EVERYONE;
    class Serializer : OrdinalSerializer<Visibility>(values())
}
