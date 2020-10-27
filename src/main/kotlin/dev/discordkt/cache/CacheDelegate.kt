package dev.discordkt.cache

import dev.discordkt.api.CacheableApiModel
import dev.discordkt.snowflake.BaseSnowflake
import dev.discordkt.snowflake.Snowflake

import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class NullableCacheDelegate<T: BaseSnowflake, V : CacheableApiModel<T>>(private val cache: Cache<*, V>, val id: Snowflake) : ReadOnlyProperty<Any?, V?> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): V? = cache[id]
    fun nonnull() = CacheDelegate(cache, id)
}

class CacheDelegate<T: BaseSnowflake, V : CacheableApiModel<T>>(private val cache: Cache<*, V>, val id: Snowflake) : ReadOnlyProperty<Any?, V> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): V = cache[id]!!
    fun nullable() = NullableCacheDelegate(cache, id)
}
