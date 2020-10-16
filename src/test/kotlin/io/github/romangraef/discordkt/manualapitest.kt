package io.github.romangraef.discordkt

import io.github.romangraef.discordkt.api.configdsl.discordKt
import io.github.romangraef.discordkt.api.user.User
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
            policy<User> {

            }
        }
    }
    readLine()
    println("shutting down...")
    discordKt.shutdown().await()
}
