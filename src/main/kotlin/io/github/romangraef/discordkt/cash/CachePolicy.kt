package io.github.romangraef.discordkt.cash

class CachePolicy<T>internal constructor(val maxCacheSize: Int?, val predicate: (T)->Boolean) {
    fun shouldCache(obj: T): Boolean = predicate(obj)
    val cacheSizeLimited: Boolean get() = maxCacheSize != null

}
