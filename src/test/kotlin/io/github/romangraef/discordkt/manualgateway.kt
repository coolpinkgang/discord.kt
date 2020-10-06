package io.github.romangraef.discordkt

import io.github.romangraef.discordkt.event.ExceptionInHandlerEvent
import io.github.romangraef.discordkt.gateway.DiscordGateway
import io.github.romangraef.discordkt.gateway.GatewayDispatchReceived
import io.github.romangraef.discordkt.models.gateway.Intent
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {
    val gateway = DiscordGateway(
        System.getenv("TOKEN")!!,
        intents = Intent.BitField(listOf(Intent.DIRECT_MESSAGES, Intent.GUILD_MESSAGES))
    )
    gateway.events.on<ExceptionInHandlerEvent> {
        it.throwable.printStackTrace()
    }
    gateway.run().join()
}
