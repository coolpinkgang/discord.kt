package io.github.romangraef.discordkt.http.routes

import io.github.romangraef.discordkt.models.channel.Channel

object Channels {

    fun GET_CHANNEL(channelId: String) = GET<Channel>("/channels/$channelId")
    fun MODIFY_CHANNEL(channelId: String) = PATCH<Channel, Channel>("/channels/$channelId")

}
