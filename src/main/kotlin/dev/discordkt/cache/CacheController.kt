package dev.discordkt.cache

import dev.discordkt.api.CacheableApiModel
import dev.discordkt.api.DiscordKt
import dev.discordkt.http.routes.Route
import dev.discordkt.snowflake.BaseSnowflake
import dev.discordkt.snowflake.SnowflakeMixin

import kotlin.reflect.KClass

class CacheController(
    val discordKt: DiscordKt,
    _caches: List<Cache<*, *>>
) {
    private val caches = mutableMapOf<KClass<*>, Cache<*, *>>()
    fun <T : CacheableApiModel<*>> lookup(modelClass: KClass<T>): Cache<*, T> = caches[modelClass] as Cache<*, T>

    init {
        _caches.forEach { caches[it.modelClass] = it }
    }

    inline fun <reified T : CacheableApiModel<*>> lookupNoCast(): Cache<*, T> = lookup(T::class)
    inline fun <reified T : BaseSnowflake, reified V : CacheableApiModel<T>> lookup() = lookupNoCast<V>() as Cache<T, V>
    @Deprecated("Deprecated", ReplaceWith("Cache::executeRoute"))
    suspend inline fun <reified RESPONSE : SnowflakeMixin, BODY, reified RESULT : CacheableApiModel<RESPONSE>>
        executeRoute(route: Route<RESPONSE, BODY>, body: BODY): RESULT {
        val model = discordKt.routeExecutor.execute(route, body)
        val cache = lookup<RESPONSE, RESULT>()
        cache.update(model)
        return cache[model.id]!!
    }
}
