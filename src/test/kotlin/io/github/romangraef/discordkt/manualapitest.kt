package io.github.romangraef.discordkt

import io.github.romangraef.discordkt.api.discordKt
import io.github.romangraef.discordkt.event.Event

suspend fun main() {
    val token = System.getenv("TOKEN")!!
    val discordKt = discordKt(token) {
        events {
            on<Event> {
                println(it)
            }
        }
    }
    readLine()
    println("shutting down...")
    discordKt.shutdown().await()
}
