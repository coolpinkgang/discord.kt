package dev.discordkt.api.channel

import dev.discordkt.api.CacheableApiModel
import dev.discordkt.cache.Cache
import dev.discordkt.http.routes.ChannelRoutes
import dev.discordkt.models.channel.Channel

interface BaseChannel : CacheableApiModel<Channel> {
    override val selfCache: Cache<Channel, out BaseChannel>
    suspend fun delete() = discordKt.routeExecutor.execute(ChannelRoutes.DELETE_CHANNEL(id))
}
