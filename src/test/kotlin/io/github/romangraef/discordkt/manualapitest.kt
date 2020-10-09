package io.github.romangraef.discordkt

import io.github.romangraef.discordkt.api.User
import io.github.romangraef.discordkt.api.configdsl.discordKt
import io.github.romangraef.discordkt.api.configdsl.CachePolicyBuilder
import io.github.romangraef.discordkt.event.Event
val CachePolicyBuilder<User>.inGuilds:(User)->Boolean get() = {true}
suspend fun main() {
    val token = System.getenv("TOKEN")!!
    val discordKt = discordKt(token) {
        events {
            on<Event> {
                println(it)
            }
        }
        cache {
            users {
                cacheIfAll(inGuilds)
                cacheSize = 500
                infiniteSize()
            }
        }
    }
    readLine()
    println("shutting down...")
    discordKt.shutdown().await()
}
