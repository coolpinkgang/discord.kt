package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.models.serial.OrdinalSerializer

import kotlinx.serialization.Serializable

@Serializable(with = IntegrationExpireBehavior.Serializer::class)
enum class IntegrationExpireBehavior {
    REMOVE_ROLE, KICK;
    class Serializer : OrdinalSerializer<IntegrationExpireBehavior>(values())
}
