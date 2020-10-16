package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.http.routes.ChannelRoutes
import io.github.romangraef.discordkt.models.channel.ChannelUpdate

interface NameableChannel : BaseChannel {
    val name get() = backing.name!!
    suspend fun rename(name: String) = discordKt.routeExecutor.execute(ChannelRoutes.MODIFY_CHANNEL(id), ChannelUpdate(name, backing.type))
}
