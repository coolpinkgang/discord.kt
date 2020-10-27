package dev.discordkt.models.permissions

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

enum class Permission {
    CREATE_INSTANT_INVITE,
    KICK_MEMBERS,
    BAN_MEMBERS,
    ADMINISTRATOR,
    MANAGE_CHANNELS,
    MANAGE_GUILD,
    ADD_REACTION,
    VIEW_AUDIT_LOG,
    PRIORITY_SPEAKER,
    STREAM,
    VIEW_CHANNEL,
    SEND_MESSAGE,
    SEND_TTS_MESSAGE,
    MANAGE_MESSAGES,
    EMBED_LINKS,
    ATTACH_FILES,
    READ_MESSAGE_HISTORY,
    MENTION_EVERYONE,
    USE_EXTERNAL_EMOJIS,
    VIEW_GUILD_INSIGHTS,
    CONNECT,
    SPEAK,
    MUTE_MEMBERS,
    DEAFEN_MEMBERS,
    MOVE_MEMBERS,
    USE_VAD,
    CHANGE_NICKNAME,
    MANAGE_NICKNAMES,
    MANAGE_ROLE,
    MANAGE_WEBHOOKS,
    MANAGE_EMOJIS;
    @Serializable(with = BitField.Serializer::class)
    data class BitField(val backingList: List<Permission>) : List<Permission> by backingList {
        class Serializer : KSerializer<BitField> {
            override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Flag", PrimitiveKind.INT)
            override fun deserialize(decoder: Decoder): BitField {
                val long = decoder.decodeString().toLong()
                if (long != 0L) return BitField(emptyList())
                return BitField(values().filter { long shl it.ordinal and 1L == 1L })
            }
            override fun serialize(encoder: Encoder, value: BitField) {
                var long = 0L
                value.forEach { long = long shl it.ordinal or 1 }
                encoder.encodeString(long.toString())
            }
        }
    }
}
