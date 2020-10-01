package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.models.serial.EnumNameSerializer

import kotlinx.serialization.Serializable

@Serializable(with = EmbedType.Serializer::class)
enum class EmbedType {
    RICH,
    IMAGE,
    VIDEO,
    GIFV,
    ARTICLE,
    LINK;
    class Serializer : EnumNameSerializer<EmbedType>(EmbedType::valueOf, false)
}
