package io.github.romangraef.discordkt.http.routes

import io.github.romangraef.discordkt.models.channel.Channel
import io.github.romangraef.discordkt.models.channel.Message
import io.github.romangraef.discordkt.models.serial.Snowflake

object Channels {
    fun GET_CHANNEL(channelId: Snowflake) = GET<Channel>("/channels/$channelId")
    fun MODIFY_CHANNEL(channelId: Snowflake) = PATCH<Channel, Channel>("/channels/$channelId")
    fun DELETE_CHANNEL(channelId: Snowflake) = DELETE<Channel>("/channels/$channelId")
    fun GET_CHANNEL_MESSAGES(
        channelId: Snowflake,
        around: Snowflake? = null,
        before: Snowflake? = null,
        after: Snowflake? = null,
        limit: Int = 50
    ): RouteWithoutBody<List<Message>> = (GET<List<Message>>("/channels/$channelId/messages")) {
        query("around", around)
        query("before", before)
        query("after", after)
        query("limit", limit)
    }
}
