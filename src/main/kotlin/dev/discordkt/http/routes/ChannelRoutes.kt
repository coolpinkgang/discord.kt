package dev.discordkt.http.routes

import dev.discordkt.models.DiscordFile
import dev.discordkt.models.channel.*
import dev.discordkt.models.invite.Invite
import dev.discordkt.models.invite.InviteCreate
import dev.discordkt.models.invite.InviteWithMetadata
import dev.discordkt.models.user.User
import dev.discordkt.snowflake.Snowflake

import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.client.request.forms.append
import io.ktor.client.request.forms.formData
import io.ktor.http.ContentType
import io.ktor.http.HttpMethod
import io.ktor.utils.io.core.writeFully

import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.json.encodeToJsonElement
import kotlinx.serialization.json.put
import kotlinx.serialization.serializer

object ChannelRoutes {
    fun GET_CHANNEL(channelId: Snowflake) = GET<Channel>("/channels/$channelId")
    fun MODIFY_CHANNEL(channelId: Snowflake) = PATCH<Channel, ChannelUpdate>("/channels/$channelId")
    fun DELETE_CHANNEL(channelId: Snowflake) = DELETE<Channel>("/channels/$channelId")
    fun GET_CHANNEL_MESSAGES(
        channelId: Snowflake,
        around: Snowflake? = null,
        before: Snowflake? = null,
        after: Snowflake? = null,
        limit: Int = 50
    ) = (GET<List<Message>>("/channels/$channelId/messages")) {
        query("around", around)
        query("before", before)
        query("after", after)
        query("limit", limit)
    }

    fun GET_CHANNEL_MESSAGE(channelId: Snowflake, messageId: Snowflake) =
        GET<Message>("/channels/$channelId/messages/$messageId")

    fun CREATE_MESSAGE(
        channelId: Snowflake,
        content: String? = null,
        file: DiscordFile? = null,
        tts: Boolean = false,
        embed: Embed? = null,
        nonce: JsonPrimitive? = null,
        allowedMentions: AllowedMentions? = null
    ) =
        Route(
            HttpMethod.Post,
            "/channels/$channelId/messages",
            JsonResultResponseMaker<Message>(serializer()),
            CustomRequestMaker<Unit> { _, json ->
                body = MultiPartFormDataContent(formData {
                    if (file != null)
                        append("file", file.name, ContentType.Application.OctetStream, file.bytes.size.toLong()) {
                            this.writeFully(file.bytes)
                        }
                    append("payload_json", json.encodeToString(buildJsonObject {
                        if (content != null)
                            put("content", content)
                        put("tts", tts)
                        if (embed != null)
                            put("embed", json.encodeToJsonElement(embed))
                        if (nonce != null)
                            put("nonce", nonce)
                        if (allowedMentions != null)
                            put("allowed_mentions", json.encodeToJsonElement(allowedMentions))
                    }))
                })
            })

    fun CROSSPOST_MESSAGE(channelId: Snowflake, messageId: Snowflake) =
        POST<Message, Unit>("/channels/$channelId/messages/$messageId/crosspost")

    fun CREATE_REACTION(channelId: Snowflake, messageId: Snowflake, emojiId: String) =
        PUT<Unit, Unit>("/channels/$channelId/messages/$messageId/reactions/$emojiId/@me")

    fun DELETE_OWN_REACTION(channelId: Snowflake, messageId: Snowflake, emojiId: String) =
        DELETE<Unit>("/channels/$channelId/messages/$messageId/reactions/$emojiId/@me")

    fun DELETE_USER_REATION(channelId: Snowflake, messageId: Snowflake, emojiId: String, userId: Snowflake) =
        DELETE<Unit>("/channels/$channelId/messages/$messageId/reactions/$emojiId/$userId")

    fun GET_REACTION(
        channelId: Snowflake,
        messageId: Snowflake,
        emojiId: String,
        before: Snowflake? = null,
        after: Snowflake? = null,
        limit: Int? = 25
    ) =
        (GET<List<User>>("/channels/$channelId/messages/$messageId/reactions/$emojiId")) {
            query("before", before)
            query("after", after)
            query("limit", limit)
        }

    fun DELETE_ALL_REACTIONS(channelId: Snowflake, messageId: Snowflake) =
        DELETE<Unit>("/channels/$channelId/messages/$messageId/reactions")

    fun DELETE_ALL_REACTUIBS_FOR_EMOJI(channelId: Snowflake, messageId: Snowflake, emojiId: String) =
        DELETE<Unit>("/channels/$channelId/messages/$messageId/reactions/$emojiId")

    fun EDIT_MESSAGE(channelId: Snowflake, messageId: Snowflake) =
        PATCH<Message, MessageEdit>("/channels/$channelId/messages/$messageId")
    fun DELETE_MESSAGE(channelId: Snowflake, messageId: Snowflake) =
        DELETE<Unit>("/channels/$channelId/messages/$messageId")
    fun BULK_DELETE_MESSAGES(channelId: Snowflake) =
        POST<Unit, MessagesBulkDelete>("/channels/$channelId/messages/bulk-delete")
    fun EDIT_CHANNEL_PERMISSIONS(channelId: Snowflake, overwriteId: Snowflake) =
        PUT<Unit, OverwriteEdit>("/channels/$channelId/permissions/$overwriteId")
    fun GET_CHANNEL_INVITES(channelId: Snowflake) =
        GET<List<InviteWithMetadata>>("/channels/$channelId/invites")
    fun CREATE_CHANNEL_INVITE(channelId: Snowflake) =
        POST<Invite, InviteCreate>("/channels/$channelId/invites")
    fun DELETE_CHANNEL_PERMISSION(channelId: Snowflake, overwriteId: Snowflake) =
        DELETE<Unit>("/channels/$channelId/permissions/$overwriteId")
    fun FOLLOW_NEWS_CHANNEL(channelId: Snowflake) =
        POST<FollowedChannel, FollowNewsChannel>("/channels/$channelId/followers")
    fun TRIGGER_TYPING_INDECATOR(channelId: Snowflake) =
        POST<Unit, Unit>("/channels/$channelId/typing")
    fun GET_PINNED_MESSAGES(channelId: Snowflake) =
        GET<List<Message>>("/channels/$channelId/pins")
    fun ADD_PINNED_CHANNEL_MESSAGE(channelId: Snowflake, messageId: Snowflake) =
        PUT<Unit, Unit>("/channels/$channelId/pins/$messageId")
    fun DELETE_PINNED_CHANNEL_MESSAGE(channelId: Snowflake, messageId: Snowflake) =
        DELETE<Unit>("/channels/$channelId/pins/$messageId")
    fun GROUP_DM_ADD_RECIPIENT(channelId: Snowflake, userId: Snowflake) =
        PUT<Unit, GroupDMAddRecipient>("/channels/$channelId/recipients/$userId")
    fun GROUP_DM_REMOVE_RECIPIENT(channelId: Snowflake, userId: Snowflake) =
        DELETE<Unit>("/channels/$channelId/recipients/$userId")
}
