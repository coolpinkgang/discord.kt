package dev.discordkt.models.channel

import dev.discordkt.models.serial.EnumNameSerializer

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
