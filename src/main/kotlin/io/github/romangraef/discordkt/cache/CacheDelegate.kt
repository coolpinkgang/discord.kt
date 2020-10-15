package io.github.romangraef.discordkt.cache

import io.github.romangraef.discordkt.api.ApiModel
import io.github.romangraef.discordkt.snowflake.Snowflake
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

class NullableCacheDelegate<T : ApiModel>(private val cache: Cache<*, T>, val id: Snowflake) : ReadOnlyProperty<Any?, T?> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T? = cache[id]
    fun nonnull() = CacheDelegate(cache, id)
}

class CacheDelegate<T : ApiModel>(private val cache: Cache<*, T>, val id: Snowflake) : ReadOnlyProperty<Any?, T> {
    override fun getValue(thisRef: Any?, property: KProperty<*>): T = cache[id]!!
    fun nullable() = NullableCacheDelegate(cache, id)
}
