package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.http.routes.ChannelRoutes
import io.github.romangraef.discordkt.models.channel.Channel
import io.github.romangraef.discordkt.models.channel.ChannelType
import io.github.romangraef.discordkt.models.channel.ChannelUpdate
import io.github.romangraef.discordkt.snowflake.Snowflake

class GuildVoiceChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake
) : GuildChildChannel {
    override val selfCache: Cache<Channel, GuildVoiceChannel> = caches.lookup()
    val bitrate: Int get() = backing.bitrate!!
    val userLimit: Int get() = backing.userLimit!!
    suspend fun changeBitrate(bitrate: Int): Unit {
        selfCache.executeRoute(ChannelRoutes.MODIFY_CHANNEL(id), ChannelUpdate(name, ChannelType.GUILD_VOICE, bitrate = bitrate))
    }
    suspend fun changeUserLimit(userLimit: Int): Unit {
        selfCache.executeRoute(ChannelRoutes.MODIFY_CHANNEL(id), ChannelUpdate(name, ChannelType.GUILD_VOICE, userLimit = userLimit))
    }
}
