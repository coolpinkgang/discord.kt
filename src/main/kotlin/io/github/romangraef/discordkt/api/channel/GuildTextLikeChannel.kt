package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.models.channel.Channel

interface GuildTextLikeChannel : GuildChannel, TextChannel {
    override val selfCache: Cache<Channel, out GuildTextLikeChannel>
    val topic get() = backing.topic
    suspend fun changeTopic(topic: String): Unit = TODO()
    suspend fun changeType(): GuildTextLikeChannel
}
