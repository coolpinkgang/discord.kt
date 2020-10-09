package io.github.romangraef.discordkt.api

import io.github.romangraef.discordkt.cache.CacheController
import io.github.romangraef.discordkt.event.AbstractEventLoop
import io.github.romangraef.discordkt.gateway.DiscordGateway
import io.github.romangraef.discordkt.http.RouteExecutor
import io.github.romangraef.discordkt.models.gateway.Intent
import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.features.addDefaultResponseValidation
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.websocket.WebSockets
import io.ktor.client.request.HttpRequestPipeline
import io.ktor.client.request.header
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.serialization.json.Json

/**
 *
 */
class DiscordKt (
    private val token: String,
    private val eventLoop: AbstractEventLoop,
    private val intents: Intent.BitField,
    val scope: CoroutineScope,
    val logger: Logger,
    val cacheController: CacheController,
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

}
