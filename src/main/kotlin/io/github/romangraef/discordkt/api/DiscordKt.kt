package io.github.romangraef.discordkt.api

import io.github.romangraef.discordkt.cash.Cash
import io.github.romangraef.discordkt.event.AbstractEventLoop
import io.github.romangraef.discordkt.event.EventLoop
import io.github.romangraef.discordkt.gateway.DiscordGateway
import io.github.romangraef.discordkt.http.RouteExecutor
import io.github.romangraef.discordkt.models.gateway.Intent

import io.ktor.client.*
import io.ktor.client.engine.okhttp.*
import io.ktor.client.features.*
import io.ktor.client.features.websocket.*
import io.ktor.client.request.*

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

import kotlinx.serialization.json.Json

import java.io.OutputStream

/**
 * Initializes and starts a bot
 */
fun discordKt(token: String, builder: DiscordKt.Builder.() -> Unit) : DiscordKt =
    DiscordKt.Builder(token).apply(builder).build()

/**
 *
 */
class DiscordKt private constructor(
    private val token: String,
    private val eventLoop: AbstractEventLoop,
    private val intents: Intent.BitField,
    val scope: CoroutineScope,
    val logger: Logger
) {
    private val httpClient: HttpClient = HttpClient(OkHttp) {
        addDefaultResponseValidation() // TODO custom response validation
        WebSockets {}
        defaultRequest {
            header("Authorization", "Bot $token")
        }
    }
    private val json: Json = Json {
        encodeDefaults = false
        ignoreUnknownKeys = true
    }
    val routeExecutor: RouteExecutor = RouteExecutor(httpClient, json)
    private val gateway: DiscordGateway = DiscordGateway(token, httpClient, json, intents, scope, eventLoop)
    val cash = Cash(this)

    init {
        initHttpClientDebug()
        start()
    }

    fun initHttpClientDebug() = httpClient.requestPipeline.intercept(HttpRequestPipeline.Send) {
        logger.debug("Sending request to ${context.method.value} '${context.url.buildString()}' with body '${context.body}'")
    }

    fun start() {
        logger.info("Starting bot...")
        gateway.run()
    }

    fun shutdown() = scope.async {
        logger.info("Shutting down the bot...")
        gateway.close(1000, "Closed by client")
    }

    class Builder internal constructor(private val token: String) {

        /**
         * The {@link CoroutineScope} in which the gateway and the event handlers will operate in
         */
        var scope: CoroutineScope = GlobalScope

        /**
         * Adds event handlers to the {@link EventLoop}
         */
        fun events(events: EventLoop.() -> Unit) = eventLoopBuilders.add(events)

        /**
         * A list of all gateway {@link Intent}s for the bot
         */
        var intents: MutableList<Intent> = mutableListOf()

        /**
         * Adds one or multiple gateway intents to {@link #intents}
         */
        fun intent(vararg intent: Intent) = intents.addAll(intent)

        /**
         * The default logging level for console logging
         */
        var consoleLoggingLevel: Logger.Level = Logger.Level.INFORMATION
            set(value) {
                field = value
                logger.outputStreams.replace(System.out, value)
            }

        /**
         * Adds an {@link OutputStream} to the {@link Logger}
         */
        fun addLogOutput(outputStream: OutputStream, level: Logger.Level) =
            logger.outputStreams.put(outputStream, level)

        private val logger: Logger = Logger(mutableMapOf(System.out to consoleLoggingLevel))

        private val eventLoopBuilders: MutableList<EventLoop.() -> Unit> = mutableListOf()

        fun build(): DiscordKt = DiscordKt(
            token,
            EventLoop(scope).apply { eventLoopBuilders.forEach { this.apply(it) } },
            Intent.BitField(intents),
            scope,
            logger
        )
    }
}
