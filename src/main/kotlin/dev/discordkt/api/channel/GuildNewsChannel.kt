package dev.discordkt.api.channel

import dev.discordkt.api.DiscordKt
import dev.discordkt.cache.Cache
import dev.discordkt.http.routes.ChannelRoutes
import dev.discordkt.models.channel.Channel
import dev.discordkt.models.channel.ChannelType
import dev.discordkt.models.channel.ChannelUpdate
import dev.discordkt.snowflake.Snowflake

class GuildNewsChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake
) : GuildTextLikeChannel {
    override val selfCache: Cache<Channel, GuildNewsChannel> = caches.lookup()
    override suspend fun changeType(): GuildTextChannel =
        selfCache.executeRoute(ChannelRoutes.MODIFY_CHANNEL(id), ChannelUpdate(name, ChannelType.GUILD_TEXT)) as GuildTextChannel
}
