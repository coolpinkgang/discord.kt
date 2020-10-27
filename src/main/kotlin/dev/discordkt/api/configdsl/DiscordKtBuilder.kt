package dev.discordkt.api.configdsl

import dev.discordkt.api.DiscordKt
import dev.discordkt.api.Logger
import dev.discordkt.event.EventLoop
import dev.discordkt.models.gateway.Intent

import java.io.OutputStream

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope

/**
 * Initializes and starts a bot
 */
fun discordKt(token: String, builder: DiscordKtBuilder.() -> Unit): DiscordKt =
    DiscordKtBuilder(token).apply(builder).build()

@DiscordKtDsl
class DiscordKtBuilder internal constructor(private val token: String) {

    val cacheControllerBuilder = CacheControllerBuilder()

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

    fun cache(block: CacheControllerBuilder.() -> Unit): Unit = cacheControllerBuilder.run(block)

    fun build(): DiscordKt = DiscordKt(
        token,
        EventLoop(scope).apply { eventLoopBuilders.forEach { this.apply(it) } },
        Intent.BitField(intents),
        scope,
        logger,
        cacheControllerBuilder.build(),
    )
}
