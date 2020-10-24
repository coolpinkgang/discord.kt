package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.models.channel.Channel
import io.github.romangraef.discordkt.snowflake.Snowflake

class GuildCategoryChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake
) : GuildChannel {
    override val selfCache: Cache<Channel, GuildCategoryChannel> = caches.lookup()
}
