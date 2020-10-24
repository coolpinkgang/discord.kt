package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.CacheableApiModel
import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.http.routes.ChannelRoutes
import io.github.romangraef.discordkt.models.channel.Channel

interface BaseChannel : CacheableApiModel<Channel> {
    override val selfCache: Cache<Channel, out BaseChannel>
    suspend fun delete() = discordKt.routeExecutor.execute(ChannelRoutes.DELETE_CHANNEL(id))
}
