package io.github.romangraef.discordkt.api

interface ApiModel {
    val discordKt: DiscordKt
    val routeExecutor get() = discordKt.routeExecutor
    val caches get() = discordKt.cacheController
}
