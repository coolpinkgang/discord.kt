package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.http.routes.ChannelRoutes
import io.github.romangraef.discordkt.models.channel.Channel
import io.github.romangraef.discordkt.models.channel.ChannelType
import io.github.romangraef.discordkt.models.channel.ChannelUpdate
import io.github.romangraef.discordkt.snowflake.Snowflake

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
