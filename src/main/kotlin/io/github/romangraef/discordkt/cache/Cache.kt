package io.github.romangraef.discordkt.cache

import io.github.romangraef.discordkt.api.ApiModel
import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.snowflake.BaseSnowflake
import io.github.romangraef.discordkt.snowflake.Snowflake
import java.util.LinkedHashMap
import kotlin.reflect.KClass

class Cache<T : BaseSnowflake, V : ApiModel>(
    val cachePolicy: CachePolicy<V>,
    val modelClass: KClass<out V>,
    val snowflakeClass: KClass<out T>,
    val transformer: (id: Snowflake, discordKt: DiscordKt) -> V,
    val discordKt: DiscordKt,
) {
    private val cachedObjects = object : LinkedHashMap<Snowflake, T>(cachePolicy.maxCacheSize ?: 100, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Snowflake, T>?): Boolean =
            cachePolicy.maxCacheSize?.let {
                it >= size
            } ?: false
    }

    fun refresh(obj: T): T = cachedObjects[obj.asSnowflake] ?: obj
    fun update(obj: T) {
        if (cachePolicy.shouldCache(transformer(obj.asSnowflake, discordKt) as V))
            cachedObjects[obj.asSnowflake] = obj
    }

    fun clear() = cachedObjects.clear()
    val size get() = cachedObjects.size
    fun getBackingObject(key: BaseSnowflake): T? = cachedObjects[key]
    operator fun get(key: BaseSnowflake): V? = cachedObjects[key]?.let { transformer(it.asSnowflake, discordKt) as V }

    fun delegate(id: BaseSnowflake) = CacheDelegate(this, id.asSnowflake)
    fun remove(vararg snowflake: Snowflake) = snowflake.forEach { cachedObjects.remove(it) }
}
