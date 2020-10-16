package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.models.channel.Channel
import io.github.romangraef.discordkt.snowflake.Snowflake

interface GuildChildChannel : GuildChannel {
    val parentId: Snowflake? get() = backing.parentId
    suspend fun getParent(): GuildCategoryChannel? =
        parentId?.let { (caches.lookup<Channel, BaseChannel>()[it] ?: TODO("load")) as GuildCategoryChannel }
}
