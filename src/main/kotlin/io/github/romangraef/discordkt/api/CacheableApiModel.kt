package io.github.romangraef.discordkt.api

import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.snowflake.BaseSnowflake
import io.github.romangraef.discordkt.snowflake.SnowflakeMixin

interface CacheableApiModel<T : BaseSnowflake> : ApiModel, SnowflakeMixin {
    val selfCache: Cache<T, out CacheableApiModel<T>>
    val backing get() = selfCache.getBackingObject(id)!!
}
