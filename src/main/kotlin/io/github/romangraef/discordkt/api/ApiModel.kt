package io.github.romangraef.discordkt.api

interface ApiModel {
    val discordKt: DiscordKt
    val caches get() = discordKt.cacheController
}
