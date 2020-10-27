package dev.discordkt.api.channel

import dev.discordkt.api.message.Message
import dev.discordkt.cache.Cache
import dev.discordkt.http.routes.ChannelRoutes
import dev.discordkt.models.channel.Channel
import dev.discordkt.models.channel.MessagesBulkDelete
import dev.discordkt.snowflake.Snowflake

interface TextChannel : BaseChannel {
    override val selfCache: Cache<Channel, out TextChannel>
    val lastMessage: Message? get() = backing.lastMessageId?.let {
        Message.lookupCacheIn(caches)[it] ?: throw RuntimeException()
    }
    suspend fun loadLastMessage(): Unit = TODO()
    suspend fun getPinedMessages(): List<Message> = TODO()
    suspend fun sendMessage(): Unit = TODO()
    suspend fun getMessage(id: Snowflake): Message = TODO()
    suspend fun getMessages(around: Message, before: Message, after: Message, limit: Int) =
        getMessages(around.id, before.id, after.id, limit)
    suspend fun getMessages(around: Snowflake, before: Snowflake, after: Snowflake, limit: Int): List<Message> = TODO()
    suspend fun bulkDeleteMessages(messages: List<Message>) = bulkDeleteMessagesBySnowflakes(messages.map { it.id })
    suspend fun bulkDeleteMessagesBySnowflakes(snowflakes: List<Snowflake>) {
        Message.lookupCacheIn(caches).remove(*snowflakes.toTypedArray())
        discordKt.routeExecutor.execute(ChannelRoutes.BULK_DELETE_MESSAGES(id), MessagesBulkDelete(snowflakes))
    }
    suspend fun triggerTypingIndicator() = discordKt.routeExecutor.execute(ChannelRoutes.TRIGGER_TYPING_INDECATOR(id))
}
