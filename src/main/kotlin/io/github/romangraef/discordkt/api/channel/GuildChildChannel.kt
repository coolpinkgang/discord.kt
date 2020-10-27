package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.models.channel.Channel
import io.github.romangraef.discordkt.snowflake.Snowflake

interface GuildChildChannel : GuildChannel {
    override val selfCache: Cache<Channel, out GuildChildChannel>
    val parent: GuildCategoryChannel? get() =
        backing.parentId?.let { id ->
            caches.lookup<Channel, BaseChannel>()[id]?.let { it as GuildCategoryChannel }
                ?: throw RuntimeException("Parent wasn't loaded")
        }
    suspend fun loadParent(): GuildCategoryChannel? = TODO()
}
