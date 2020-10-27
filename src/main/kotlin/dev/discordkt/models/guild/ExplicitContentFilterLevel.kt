package dev.discordkt.models.guild

import dev.discordkt.models.serial.OrdinalSerializer

import kotlinx.serialization.Serializable

@Serializable(with = ExplicitContentFilterLevel.Serializer::class)
enum class ExplicitContentFilterLevel {
    DISABLED,
    MEMBERS_WITHOUT_ROLES,
    ALL_MEMBERS;
    class Serializer : OrdinalSerializer<ExplicitContentFilterLevel>(values())
}
