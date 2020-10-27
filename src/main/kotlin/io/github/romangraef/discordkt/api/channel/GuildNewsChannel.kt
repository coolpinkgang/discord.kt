package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.http.routes.ChannelRoutes
import io.github.romangraef.discordkt.models.channel.Channel
import io.github.romangraef.discordkt.models.channel.ChannelType
import io.github.romangraef.discordkt.models.channel.ChannelUpdate
import io.github.romangraef.discordkt.snowflake.Snowflake

class GuildNewsChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake
) : GuildTextLikeChannel {
    override val selfCache: Cache<Channel, GuildNewsChannel> = caches.lookup()
    override suspend fun changeType(): GuildTextChannel =
        selfCache.executeRoute(ChannelRoutes.MODIFY_CHANNEL(id), ChannelUpdate(name, ChannelType.GUILD_TEXT)) as GuildTextChannel
}
