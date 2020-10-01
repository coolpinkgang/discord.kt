package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.models.serial.OrdinalSerializer

import kotlinx.serialization.Serializable

@Serializable(with = ChannelType.Serializer::class)
enum class ChannelType {
    GUILD_TEXT,
    DM,
    GUILD_VOICE,
    GROUP_DM,
    GUILD_CATEGORY,
    GUILD_NEWS,
    GUILD_STORE;
    class Serializer : OrdinalSerializer<ChannelType>(values())
}
