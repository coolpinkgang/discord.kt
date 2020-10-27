package dev.discordkt.api.configdsl

import dev.discordkt.api.ApiModel
import dev.discordkt.api.CacheableApiModel
import dev.discordkt.api.DiscordKt
import dev.discordkt.cache.Cache
import dev.discordkt.cache.CachePolicy
import dev.discordkt.snowflake.BaseSnowflake
import dev.discordkt.snowflake.Snowflake

import kotlin.reflect.KClass

data class PartialCache<T : BaseSnowflake, V : CacheableApiModel<T>>(
    val policy: CachePolicy<V>,
    val modelClass: KClass<out V>,
    val snowflakeClass: KClass<out T>,
    val transformer: (id: Snowflake, discordKt: DiscordKt) -> V
) {
    fun complete(discordKt: DiscordKt): Cache<T, V> = Cache(
        policy, modelClass, snowflakeClass,
        transformer, discordKt
    )
}
