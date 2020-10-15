package io.github.romangraef.discordkt.cache

import io.github.romangraef.discordkt.api.ApiModel
import io.github.romangraef.discordkt.api.DiscordKt
import kotlin.reflect.KClass

class CacheController(
    discordKt: DiscordKt,
    _caches: List<Cache<*, *>>
) {
    private val caches = mutableMapOf<KClass<*>, Cache<*, *>>()
    fun <T : ApiModel> lookup(modelClass: KClass<T>): Cache<*, T> = caches[modelClass] as Cache<*, T>

    init {
        _caches.forEach { caches[it.modelClass] = it }
    }

    inline fun <reified T : ApiModel> lookup(): Cache<*, T> = lookup(T::class)

}
