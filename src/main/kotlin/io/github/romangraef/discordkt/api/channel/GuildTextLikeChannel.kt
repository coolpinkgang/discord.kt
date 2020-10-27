package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.http.routes.ChannelRoutes
import io.github.romangraef.discordkt.models.channel.Channel
import io.github.romangraef.discordkt.models.channel.ChannelType
import io.github.romangraef.discordkt.models.channel.ChannelUpdate

interface GuildTextLikeChannel : GuildChannel, TextChannel {
    override val selfCache: Cache<Channel, out GuildTextLikeChannel>
    val topic get() = backing.topic
    suspend fun changeTopic(topic: String) {
        selfCache.executeRoute(ChannelRoutes.MODIFY_CHANNEL(id), ChannelUpdate(name, backing.type, topic = topic))
    }
    suspend fun changeType(): GuildTextLikeChannel
}
