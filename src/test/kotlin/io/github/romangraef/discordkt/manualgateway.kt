package io.github.romangraef.discordkt

import io.github.romangraef.discordkt.event.ExceptionInHandlerEvent
import io.github.romangraef.discordkt.gateway.DiscordGateway
import io.github.romangraef.discordkt.gateway.GatewayDispatchReceived
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val gateway = DiscordGateway(System.getenv("TOKEN")!!)
    gateway.events.on<ExceptionInHandlerEvent> {
        it.throwable.printStackTrace()
    }
    gateway.events.on<GatewayDispatchReceived> {
        println(it.data)
    }
    gateway.run().join()
}
