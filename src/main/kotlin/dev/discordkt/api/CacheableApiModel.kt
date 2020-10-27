package dev.discordkt.api

import dev.discordkt.cache.Cache
import dev.discordkt.snowflake.BaseSnowflake
import dev.discordkt.snowflake.SnowflakeMixin

interface CacheableApiModel<T : BaseSnowflake> : ApiModel, SnowflakeMixin {
    val selfCache: Cache<T, out CacheableApiModel<T>>
    val backing get() = selfCache.getBackingObject(id)!!
}
