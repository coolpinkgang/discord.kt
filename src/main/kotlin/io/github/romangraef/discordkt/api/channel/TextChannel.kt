package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.message.Message

import io.github.romangraef.discordkt.http.routes.ChannelRoutes
import io.github.romangraef.discordkt.models.channel.MessagesBulkDelete

import io.github.romangraef.discordkt.snowflake.Snowflake

import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async

interface TextChannel : BaseChannel {
    val lastMessage: Message?
    fun sendMessage(): Deferred<Message> = discordKt.scope.async {
        TODO()
    }
    fun getMessages(around: Message?, before: Message?, after: Message?, limit: Int = 50): Any =
        getMessages(around?.asSnowflake, before?.asSnowflake, after?.asSnowflake, limit)
    fun getMessages(around: Snowflake?, before: Snowflake?, after: Snowflake?, limit: Int = 50): Any = TODO()
    fun getMessage(snowflake: Snowflake): Any = TODO()
    fun bulkDeleteMessages(messages: List<Message>) = bulkDeleteMessagesFromSnowfalkes(messages.map { it.asSnowflake })
    fun bulkDeleteMessagesFromSnowfalkes(messages: List<Snowflake>) = discordKt.scope.async {
        discordKt.routeExecutor.execute(ChannelRoutes.BULK_DELETE_MESSAGES(id), MessagesBulkDelete(messages))
    }
    fun triggerTypingIndicator() = discordKt.scope.async {
        discordKt.routeExecutor.execute(ChannelRoutes.TRIGGER_TYPING_INDECATOR(id))
    }
    fun getPinnedMessages(): Any = TODO()
}
