package io.github.romangraef.discordkt.api

import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.snowflake.BaseSnowflake
import io.github.romangraef.discordkt.snowflake.Snowflake
import io.github.romangraef.discordkt.snowflake.SnowflakeMixin

interface ApiModel {
    val discordKt: DiscordKt
    val caches get() = discordKt.cacheController
}

interface IdApiModel : ApiModel, SnowflakeMixin

interface CachedApiModel<T : BaseSnowflake> : IdApiModel {
    val selfCache: Cache<T, out CachedApiModel<T>>
    val backing get() = selfCache.getBackingObject(id)!!
}
