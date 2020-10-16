package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.CachedApiModel
import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.http.routes.ChannelRoutes
import io.github.romangraef.discordkt.models.channel.Channel

interface BaseChannel : CachedApiModel<Channel> {
    override val selfCache: Cache<Channel, out BaseChannel>
        get() = caches.lookup()
    suspend fun delete() = discordKt.routeExecutor.execute(ChannelRoutes.DELETE_CHANNEL(id))
}
