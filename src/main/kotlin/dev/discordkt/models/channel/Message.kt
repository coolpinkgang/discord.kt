@file:UseSerializers(ISO8601Serializer::class)
package dev.discordkt.models.channel

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.snowflake.SnowflakeMixin
import dev.discordkt.models.guild.GuildMember
import dev.discordkt.models.user.User
import dev.discordkt.models.serial.ISO8601Serializer

import java.time.OffsetDateTime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

@Serializable
data class Message(
    override val id: Snowflake,
    @SerialName("channel_id")
    val channelId: Snowflake,
    @SerialName("guild_id")
    val guildId: Snowflake? = null,
    val author: User,
    val member: GuildMember? = null,
    val content: String,
    val timestamp: OffsetDateTime,
    @SerialName("edited_timestamp")
    val editedTimestamp: OffsetDateTime?,
    val tts: Boolean,
    @SerialName("mention_everyone")
    val mentionEveryone: Boolean,
    val mentions: List<User>,
    @SerialName("mention_roles")
    val mentionRoles: List<Snowflake>,
    @SerialName("mention_channels")
    val mentionChannels: List<ChannelMention>? = emptyList(),
    val attachments: List<Attachment>,
    val embeds: List<Embed>,
    val reactions: List<Reaction>? = null,
    val nonce: Int? = null, //TODO: or string
    val pinned: Boolean,
    @SerialName("webhook_id")
    val webhookId: Snowflake? = null,
    val type: MessageType,
    val activity: MessageActivity? = null,
    val application: MessageApplication? = null,
    @SerialName("message_reference")
    val messageReference: MessageReference? = null,
    val flags: MessageFlag.BitField? = null
) : SnowflakeMixin
