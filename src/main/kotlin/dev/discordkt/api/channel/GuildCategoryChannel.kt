package dev.discordkt.api.channel

import dev.discordkt.api.DiscordKt
import dev.discordkt.cache.Cache
import dev.discordkt.models.channel.Channel
import dev.discordkt.snowflake.Snowflake

class GuildCategoryChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake
) : GuildChannel {
    override val selfCache: Cache<Channel, GuildCategoryChannel> = caches.lookup()
    val children: List<GuildChildChannel> get() = TODO()
    fun loadChildren() {

    }
}
