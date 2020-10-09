package io.github.romangraef.discordkt.cash

import io.github.romangraef.discordkt.snowflake.BaseSnowflake
import io.github.romangraef.discordkt.snowflake.Snowflake
import java.util.LinkedHashMap

class Cache<T : BaseSnowflake>(val cachePolicy: CachePolicy<T>) {
    private val cachedObjects = object : LinkedHashMap<Snowflake, T>(cachePolicy.maxCacheSize ?: 100, 0.75f, true) {
        override fun removeEldestEntry(eldest: MutableMap.MutableEntry<Snowflake, T>?): Boolean =
            cachePolicy.maxCacheSize?.let {
                it >= size
            } ?: false
    }


    fun refresh(obj: T): T = cachedObjects[obj.asSnowflake] ?: obj
    fun update(obj: T) {
        if (cachePolicy.shouldCache(obj))
            cachedObjects[obj.asSnowflake] = obj
    }

    fun clear() = cachedObjects.clear()
    val size get() = cachedObjects.size
    operator fun get(key: Snowflake): T? = cachedObjects[key]

}
