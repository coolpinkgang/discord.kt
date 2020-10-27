package dev.discordkt.api.channel

import dev.discordkt.api.DiscordKt
import dev.discordkt.cache.Cache
import dev.discordkt.http.routes.ChannelRoutes
import dev.discordkt.models.channel.Channel
import dev.discordkt.models.channel.ChannelType
import dev.discordkt.models.channel.ChannelUpdate
import dev.discordkt.snowflake.Snowflake

class GuildTextChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake
) : GuildTextLikeChannel {
    override val selfCache: Cache<Channel, GuildTextChannel> = caches.lookup()
    val ratePerUserLimit: Int = backing.rateLimitPerUser!!
    override suspend fun changeType(): GuildNewsChannel =
        selfCache.executeRoute(ChannelRoutes.MODIFY_CHANNEL(id), ChannelUpdate(name, ChannelType.GUILD_NEWS)) as GuildNewsChannel
    suspend fun changeRatePerUserLimit(limit: Int) {
        selfCache.executeRoute(ChannelRoutes.MODIFY_CHANNEL(id), ChannelUpdate(name, ChannelType.GUILD_TEXT, rateLimitPerUser = limit))
    }
}
