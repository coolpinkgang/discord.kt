package dev.discordkt.api.channel

import dev.discordkt.cache.Cache
import dev.discordkt.http.routes.ChannelRoutes
import dev.discordkt.models.channel.Channel
import dev.discordkt.models.channel.ChannelUpdate

interface NameableChannel : BaseChannel {
    override val selfCache: Cache<Channel, out NameableChannel>
    val name get() = backing.name!!
    suspend fun rename(name: String) = discordKt.routeExecutor.execute(ChannelRoutes.MODIFY_CHANNEL(id), ChannelUpdate(name, backing.type))
}
