package dev.discordkt.api.channel

import dev.discordkt.cache.Cache
import dev.discordkt.http.routes.ChannelRoutes
import dev.discordkt.models.channel.Channel
import dev.discordkt.models.channel.ChannelType
import dev.discordkt.models.channel.ChannelUpdate

interface GuildTextLikeChannel : GuildChannel, TextChannel {
    override val selfCache: Cache<Channel, out GuildTextLikeChannel>
    val topic get() = backing.topic
    suspend fun changeTopic(topic: String) {
        selfCache.executeRoute(ChannelRoutes.MODIFY_CHANNEL(id), ChannelUpdate(name, backing.type, topic = topic))
    }
    suspend fun changeType(): GuildTextLikeChannel
}
