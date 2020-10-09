package io.github.romangraef.discordkt.api.configdsl

import io.github.romangraef.discordkt.cash.CachePolicy

@DiscordKtDsl
class CachePolicyBuilder<T>(var cacheSize: Int? = null) {
    var predicate: (T) -> Boolean = { true }
    fun cacheIfAny(vararg predicates: (T) -> Boolean) {
        predicate = { obj -> predicates.any { it(obj) } }
    }

    fun cacheIfAll(vararg predicates: (T) -> Boolean) {
        predicate = { obj -> predicates.all { it(obj) } }
    }

    fun infiniteSize() {
        cacheSize = null
    }

    fun build() =  CachePolicy(cacheSize, predicate)

}
