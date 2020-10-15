package io.github.romangraef.discordkt.api.configdsl

import io.github.romangraef.discordkt.api.ApiModel
import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.cache.CachePolicy
import io.github.romangraef.discordkt.snowflake.BaseSnowflake
import io.github.romangraef.discordkt.snowflake.Snowflake
import kotlin.reflect.KClass

data class PartialCache<V : ApiModel>(
    val policy: CachePolicy<V>,
    val modelClass: KClass<out V>,
    val snowflakeClass: KClass<out BaseSnowflake>,
    val transformer: (id: Snowflake, discordKt: DiscordKt) -> V
) {
    fun complete(discordKt: DiscordKt): Cache<BaseSnowflake, V> = Cache(
        policy, modelClass, snowflakeClass,
        transformer, discordKt
    )
}
