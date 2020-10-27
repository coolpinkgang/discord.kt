package dev.discordkt.api.channel

import dev.discordkt.api.DiscordKt
import dev.discordkt.cache.Cache
import dev.discordkt.models.channel.Channel
import dev.discordkt.snowflake.Snowflake

class GuildStoreChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake
) : GuildChildChannel {
    override val selfCache: Cache<Channel, GuildStoreChannel> = caches.lookup()
}
