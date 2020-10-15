package io.github.romangraef.discordkt.api

import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.snowflake.BaseSnowflake
import io.github.romangraef.discordkt.snowflake.Snowflake

interface ApiModel {
    val discordKt: DiscordKt
    val caches get() = discordKt.cacheController
}

interface IdApiModel : ApiModel {
    val id: Snowflake
}

interface CachedApiModel<T : BaseSnowflake> : IdApiModel {
    val selfCache: Cache<T, out CachedApiModel<T>>
    val backing get() = selfCache.getBackingObject(id)!!
}
