package io.github.romangraef.discordkt.api.message

import io.github.romangraef.discordkt.api.ApiModel
import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.api.User
import io.github.romangraef.discordkt.api.channel.BaseChannel
import io.github.romangraef.discordkt.api.channel.TextChannel
import io.github.romangraef.discordkt.api.guild.Guild
import io.github.romangraef.discordkt.api.guild.Member
import io.github.romangraef.discordkt.api.permission.Role
import io.github.romangraef.discordkt.api.webhook.Webhook

import io.github.romangraef.discordkt.http.routes.ChannelRoutes
import io.github.romangraef.discordkt.models.channel.MessageEdit

import io.github.romangraef.discordkt.snowflake.Snowflake
import io.github.romangraef.discordkt.snowflake.SnowflakeMixin
import kotlinx.coroutines.async

import java.time.OffsetDateTime

class Message(
    override val discordKt: DiscordKt,
    override val id: Snowflake,
    val channel: TextChannel,
    val guild: Guild?,
    val author: User,
    val member: Member?,
    val content: String,
    val timestamp: OffsetDateTime,
    val editedTimestamp: OffsetDateTime,
    val tts: Boolean,
    val mentionEveryone: Boolean,
    val mentions: List<User>,
    val mentionRoles: List<Role>,
    val mentionChannels: List<BaseChannel>,
    val attachments: List<Attachment>,
    val embeds: List<Embed>,
    val reactions: List<Reaction>,
    val pinned: Boolean,
    val webhook: Webhook?,
    val type: MessageType,
    val activity: MessageActivity,
    val application: MessageApplication,
    val messageReference: Message?
) : SnowflakeMixin, ApiModel {

    fun react(emoji: String) = discordKt.scope.async {
        discordKt.routeExecutor.execute(ChannelRoutes.CREATE_REACTION(channel.id, id, emoji))
    }

    fun deleteOwnReaction(emoji: String) = discordKt.scope.async {
        discordKt.routeExecutor.execute(ChannelRoutes.DELETE_OWN_REACTION(channel.id, id, emoji))
    }

    fun deleteUserReaction(emoji: String, user: User) = discordKt.scope.async {
        discordKt.routeExecutor.execute(ChannelRoutes.DELETE_USER_REATION(channel.id, id, emoji, user.id))
    }

    fun getWhoReacted(emoji: String, before: User?, after: User?, limit: Int = 25) = discordKt.scope.async {
        discordKt.routeExecutor.execute(ChannelRoutes.GET_REACTION(channel.id, id, emoji, before?.id, after?.id, limit))
            .map { User.of(it) }
    }

    fun deleteAllReactions() = discordKt.scope.async {
        discordKt.routeExecutor.execute(ChannelRoutes.DELETE_ALL_REACTIONS(channel.id, id))
    }

    fun deleteAllReactionsForEmoji(emoji: String) = discordKt.scope.async {
        discordKt.routeExecutor.execute(ChannelRoutes.DELETE_ALL_REACTUIBS_FOR_EMOJI(channel.id, id, emoji))
    }

    fun edit(content: String? = null, embed: Embed? = null, flags: List<MessageFlag>? = null) = discordKt.scope.async {
        discordKt.routeExecutor.execute(
            ChannelRoutes.EDIT_MESSAGE(channel.id, id),
            MessageEdit(content, embed.toSerialEmbed(), flags?.let { io.github.romangraef.discordkt.models.channel.MessageFlag.BitField(it) })
        )
    }

    fun delete() = discordKt.scope.async {
        discordKt.routeExecutor.execute(ChannelRoutes.DELETE_MESSAGE(channel.id, id))
    }

    companion object {
        fun of(message: io.github.romangraef.discordkt.models.channel.Message, discordKt: DiscordKt): Message = TODO()
    }
}
