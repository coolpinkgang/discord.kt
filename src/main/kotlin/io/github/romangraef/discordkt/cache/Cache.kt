package io.github.romangraef.discordkt.cache

import io.github.romangraef.discordkt.api.CacheableApiModel
import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.http.routes.Route
import io.github.romangraef.discordkt.snowflake.BaseSnowflake
import io.github.romangraef.discordkt.snowflake.Snowflake
import kotlin.reflect.KClass

class Cache<T : BaseSnowflake, V : CacheableApiModel<T>>(
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
        if (cachePolicy.shouldCache(transformer(obj.asSnowflake, discordKt)))
            cachedObjects[obj.asSnowflake] = obj
    }

    fun clear() = cachedObjects.clear()
    val size get() = cachedObjects.size
    fun getBackingObject(key: BaseSnowflake): T? = cachedObjects[key]
    operator fun get(key: BaseSnowflake): V? = cachedObjects[key]?.let { transformer(it.asSnowflake, discordKt) }

    fun delegate(id: BaseSnowflake) = CacheDelegate(this, id.asSnowflake)
    fun remove(vararg snowflake: Snowflake) = snowflake.forEach { cachedObjects.remove(it) }

    suspend fun <BODY> executeRoute(route: Route<T, BODY>, body: BODY): V {
        val model = discordKt.routeExecutor.execute(route, body)
        this.update(model)
        return this[model]!!
    }
}
