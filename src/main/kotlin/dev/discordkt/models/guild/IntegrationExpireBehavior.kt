package dev.discordkt.models.guild

import dev.discordkt.models.serial.OrdinalSerializer

import kotlinx.serialization.Serializable

@Serializable(with = IntegrationExpireBehavior.Serializer::class)
enum class IntegrationExpireBehavior {
    REMOVE_ROLE, KICK;
    class Serializer : OrdinalSerializer<IntegrationExpireBehavior>(values())
}
