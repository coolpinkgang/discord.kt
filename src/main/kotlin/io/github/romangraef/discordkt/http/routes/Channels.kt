package io.github.romangraef.discordkt.http.routes

import io.github.romangraef.discordkt.models.channel.Channel
import io.github.romangraef.discordkt.models.serial.Snowflake

object Channels {
    fun GET_CHANNEL(channelId: Snowflake) = GET<Channel>("/channels/$channelId")
    fun MODIFY_CHANNEL(channelId: Snowflake) = PATCH<Channel, Channel>("/channels/$channelId")
    fun DELETE_CHANNEL(channelId: Snowflake) = DELETE<Channel>("/channels/$channelId")
}
