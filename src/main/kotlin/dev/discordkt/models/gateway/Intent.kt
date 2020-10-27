package dev.discordkt.models.gateway

import dev.discordkt.models.serial.FlagsSerializer

import kotlinx.serialization.Serializable

enum class Intent {
    GUILDS,
    GUILD_MEMBERS,
    GUILD_BANS,
    GUILD_EMOJIS,
    GUILD_INTEGRATIONS,
    GUILD_WEBHOOKS,
    GUILD_INVITES,
    GUILD_VOICE_STATES,
    GUILD_PRESENCES,
    GUILD_MESSAGES,
    GUILD_MESSAGE_REACTIONS,
    GUILD_MESSAGE_TYPING,
    DIRECT_MESSAGES,
    DIRECT_MESSAGE_REACTIONS,
    DIRECT_MESSAGE_TYPING;

    companion object {
        val ALL_INTENTS
            get() = BitField(values().toList())
    }

    @Serializable(with = BitField.Serializer::class)
    data class BitField(val backingList: List<Intent>) : List<Intent> by backingList {
        object Serializer : FlagsSerializer<Intent, BitField>(values(), ::BitField)
    }
}
