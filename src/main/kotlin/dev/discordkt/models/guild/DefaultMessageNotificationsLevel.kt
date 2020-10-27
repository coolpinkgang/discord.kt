package dev.discordkt.models.guild

import dev.discordkt.models.serial.OrdinalSerializer

import kotlinx.serialization.Serializable

@Serializable(with = DefaultMessageNotificationsLevel.Serializer::class)
enum class DefaultMessageNotificationsLevel {
    ALL_MESSAGES,
    ONLY_MENTIONS;
    class Serializer : OrdinalSerializer<DefaultMessageNotificationsLevel>(values())
}
