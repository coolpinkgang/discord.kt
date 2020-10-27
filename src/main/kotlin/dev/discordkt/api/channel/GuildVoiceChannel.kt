package dev.discordkt.api.channel

import dev.discordkt.api.DiscordKt
import dev.discordkt.cache.Cache
import dev.discordkt.http.routes.ChannelRoutes
import dev.discordkt.models.channel.Channel
import dev.discordkt.models.channel.ChannelType
import dev.discordkt.models.channel.ChannelUpdate
import dev.discordkt.snowflake.Snowflake

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
