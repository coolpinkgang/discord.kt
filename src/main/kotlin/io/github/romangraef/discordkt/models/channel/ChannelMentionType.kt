package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.models.serial.EnumNameSerializer

import kotlinx.serialization.Serializable

@Serializable(with = ChannelMentionType.Serializer::class)
enum class ChannelMentionType {
    GUILD_TEXT,
    DM,
    GUILD_VOICE,
    GROUP_DM,
    GUILD_CATEGORY,
    GUILD_NEWS,
    GUILD_STORE;
    class Serializer : EnumNameSerializer<ChannelMentionType>(ChannelMentionType::valueOf)
}
