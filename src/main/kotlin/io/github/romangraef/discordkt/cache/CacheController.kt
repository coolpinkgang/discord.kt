package io.github.romangraef.discordkt.cache

import io.github.romangraef.discordkt.api.ApiModel
import io.github.romangraef.discordkt.api.CacheableApiModel
import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.http.routes.Route
import io.github.romangraef.discordkt.snowflake.BaseSnowflake
import io.github.romangraef.discordkt.snowflake.SnowflakeMixin
import kotlin.reflect.KClass

class CacheController(
    val discordKt: DiscordKt,
    _caches: List<Cache<*, *>>
) {
    private val caches = mutableMapOf<KClass<*>, Cache<*, *>>()
    fun <T : ApiModel> lookup(modelClass: KClass<T>): Cache<*, T> = caches[modelClass] as Cache<*, T>

    init {
        _caches.forEach { caches[it.modelClass] = it }
    }

    inline fun <reified T : ApiModel> lookupNoCast(): Cache<*, T> = lookup(T::class)
    inline fun <reified T : BaseSnowflake, reified V : ApiModel> lookup() = lookupNoCast<V>() as Cache<T, V>
    suspend inline fun <reified RESPONSE : SnowflakeMixin, BODY, reified RESULT : CacheableApiModel<RESPONSE>>
        executeRoute(route: Route<RESPONSE, BODY>, body: BODY): RESULT {
        val model = discordKt.routeExecutor.execute(route, body)
        val cache = lookup<RESPONSE, RESULT>()
        cache.update(model)
        return cache[model.id]!!
    }
}
