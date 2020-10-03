package io.github.romangraef.discordkt.models.gateway

import io.github.romangraef.discordkt.models.serial.OrdinalSerializer

import kotlinx.serialization.Serializable

@Serializable(with = ActivityType.Serializer::class)
enum class ActivityType {
    GAME,
    STREAMING,
    LISTENING,
    CUSTOM;
    class Serializer : OrdinalSerializer<ActivityType>(values())
}
