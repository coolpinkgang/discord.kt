package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.http.routes.ChannelRoutes
import io.github.romangraef.discordkt.models.channel.Channel
import io.github.romangraef.discordkt.models.channel.ChannelUpdate

interface NameableChannel : BaseChannel {
    override val selfCache: Cache<Channel, out NameableChannel>
    val name get() = backing.name!!
    suspend fun rename(name: String) = discordKt.routeExecutor.execute(ChannelRoutes.MODIFY_CHANNEL(id), ChannelUpdate(name, backing.type))
}
