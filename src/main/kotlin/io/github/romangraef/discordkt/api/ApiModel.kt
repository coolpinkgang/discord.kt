package io.github.romangraef.discordkt.api

import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.snowflake.BaseSnowflake

interface ApiModel {
    val discordKt: DiscordKt
    val caches get() = discordKt.cacheController
}

interface CachedApiModel<T : BaseSnowflake> : ApiModel {
    val selfCache: Cache<T, CachedApiModel<T>>
}
