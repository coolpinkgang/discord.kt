package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.models.serial.EnumNameSerializer

import kotlinx.serialization.Serializable

@Serializable(with = AllowedMentionType.Serializer::class)
enum class AllowedMentionType {
    ROLES,
    USERS,
    EVERYONE;
    class Serializer : EnumNameSerializer<AllowedMentionType>(AllowedMentionType::valueOf, false)
}
