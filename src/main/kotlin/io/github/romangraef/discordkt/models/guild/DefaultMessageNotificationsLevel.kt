package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.models.serial.OrdinalSerializer

import kotlinx.serialization.Serializable

@Serializable(with = DefaultMessageNotificationsLevel.Serializer::class)
enum class DefaultMessageNotificationsLevel {
    ALL_MESSAGES,
    ONLY_MENTIONS;
    class Serializer : OrdinalSerializer<DefaultMessageNotificationsLevel>(values())
}
