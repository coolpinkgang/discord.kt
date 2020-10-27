package dev.discordkt

import dev.discordkt.api.configdsl.discordKt
import dev.discordkt.api.user.User
import dev.discordkt.event.Event

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
