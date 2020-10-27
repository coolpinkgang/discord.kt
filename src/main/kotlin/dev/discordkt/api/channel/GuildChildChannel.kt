package dev.discordkt.api.channel

import dev.discordkt.cache.Cache
import dev.discordkt.models.channel.Channel
import dev.discordkt.snowflake.Snowflake

interface GuildChildChannel : GuildChannel {
    override val selfCache: Cache<Channel, out GuildChildChannel>
    val parent: GuildCategoryChannel? get() =
        backing.parentId?.let { id ->
            caches.lookup<Channel, BaseChannel>()[id]?.let { it as GuildCategoryChannel }
                ?: throw RuntimeException("Parent wasn't loaded")
        }
    suspend fun loadParent(): GuildCategoryChannel? = TODO()
}
