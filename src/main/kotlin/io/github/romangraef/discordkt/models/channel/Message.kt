@file:UseSerializers(ISO8601Serializer::class)
package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.models.guild.GuildMember
import io.github.romangraef.discordkt.models.permissions.Role
import io.github.romangraef.discordkt.models.user.User
import io.github.romangraef.discordkt.models.serial.ISO8601Serializer
import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.serial.SnowflakeMixin

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
    val mentionRoles: List<Role>,
    @SerialName("mention_channels")
    val mentionChannels: List<ChannelMention>? = emptyList(),
    val attachments: List<Attachment>,
    val embeds: List<Embed>,
    val reactions: List<Reaction>? = null,
    val nonce: Int? = null, //TODO: or string
    val pinned: Boolean,
    @SerialName("webhook_id")
    val webhookId: Snowflake,
    val type: MessageType,
    val activity: MessageActivity? = null,
    val application: MessageApplication? = null,
    @SerialName("message_reference")
    val messageReference: MessageReference? = null,
    val flags: MessageFlag.BitField? = null
) : SnowflakeMixin()
