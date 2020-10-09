package io.github.romangraef.discordkt

import io.github.romangraef.discordkt.api.configdsl.discordKt
import io.github.romangraef.discordkt.api.configdsl.isGuildMessage
import io.github.romangraef.discordkt.api.configdsl.isPinned
import io.github.romangraef.discordkt.event.Event

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
                cacheSize = 500
            }
            messages {
                cacheIfAll(isGuildMessage, isPinned)
                infiniteSize()
            }
        }
    }
    readLine()
    println("shutting down...")
    discordKt.shutdown().await()
}
