package dev.discordkt.gateway

import dev.discordkt.event.AbstractEventLoop
import dev.discordkt.event.Event
import dev.discordkt.event.EventLoop
import dev.discordkt.models.gateway.GatewayEvent
import dev.discordkt.models.gateway.Hello
import dev.discordkt.models.gateway.Identify
import dev.discordkt.models.gateway.IdentifyConnection
import dev.discordkt.models.gateway.Intent
import dev.discordkt.models.gateway.Ready
import dev.discordkt.models.gateway.Resume
import dev.discordkt.models.serial.IDBasedSerializer
import dev.discordkt.models.serial.IDEnum

import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.DefaultClientWebSocketSession
import io.ktor.client.features.websocket.webSocketSession
import io.ktor.client.request.header
import io.ktor.client.request.url
import io.ktor.http.cio.websocket.CloseReason
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readText

import java.util.concurrent.Executors
import java.net.SocketTimeoutException as SocketTimeoutException1

import kotlin.random.Random
import kotlin.random.nextInt

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.asCoroutineDispatcher
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.serializer

@Serializable(with = GatewayOperator.Serializer::class)
enum class GatewayOperator(override val id: Int) : IDEnum {
    DISPATCH(0),
    HEARTBEAT(1),
    IDENTIFY(2),
    PRESENCE(3),
    VOICE_STATE(4),
    RESUME(6),
    RECONNECT(7),
    MEMBER_CHUNK_REQUEST(8),
    INVALIDATE_SESSION(9),
    HELLO(10),
    HEARTBEAT_ACK(11),
    GUILD_SYNC(12);

    object Serializer : IDBasedSerializer<GatewayOperator>(values())
}

@Serializable
data class GatewayPayload(
    @SerialName("op")
    val operator: GatewayOperator,
    @SerialName("d")
    val data: JsonElement? = null,
    @SerialName("s")
    val sequenceNumber: Int? = null,
    @SerialName("t")
    val type: String? = null,
)

data class RawGatewayReceivedEvent(val payload: GatewayPayload) : Event
data class UnknownGatewayOpcodeReceived(val payload: GatewayPayload) : Event
data class GatewayDispatchReceived(val data: JsonElement, val type: String) : Event
class DiscordGateway(
    val token: String,
    val client: HttpClient,
    val json: Json,
    val intents: Intent.BitField = Intent.ALL_INTENTS,
    val scope: CoroutineScope = GlobalScope,
    val events: AbstractEventLoop = EventLoop(scope),
) {
    init {
        events.on<RawGatewayReceivedEvent> {
            if (it.payload.sequenceNumber != null)
                lastSequenceNumber = it.payload.sequenceNumber
            when (it.payload.operator) {
                GatewayOperator.DISPATCH -> dispatchEvent(it.payload)
                GatewayOperator.HEARTBEAT -> sendHeartbeat()
                GatewayOperator.RECONNECT -> reconnectWithDelay()
                GatewayOperator.INVALIDATE_SESSION -> println("TODO: invalidate session")
                GatewayOperator.HELLO -> setupHeartbeat(it.payload)
                GatewayOperator.HEARTBEAT_ACK -> lastHeartbeatAckAtNs = System.nanoTime()
                else -> events.emit(UnknownGatewayOpcodeReceived(it.payload))
            }
        }
    }

    private var lastHeartbeatSentAtNs = 0L
    private var lastHeartbeatAckAtNs = 0L
    private var heartbeatIntervalMs = 0L
    private var websocketSession: DefaultClientWebSocketSession? = null
    private var reconnectAfter = 0
    private var sessionId: String? = null
    private val mutex = Mutex()
    var latencyMs: Long = -1L
        private set
    var lastSequenceNumber: Int? = null

    private suspend fun loop() {
        mutex.withLock {
            if (websocketSession != null) return@loop
            while (reconnectAfter >= 0) {
                delay(reconnectAfter.toLong())
                reconnectAfter = -1
                println("Starting websocket session")
                try {
                    websocketSession = client.webSocketSession {
                        url(host = "gateway.discord.gg", path = "/?v=8&encoding=json", scheme = "wss")
                        header("Accept-Encoding", "gzip")
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    println("Failed to reconnect")
                    reconnectAfter = 5000
                    continue
                }
                try {
                    while (!websocketSession!!.incoming.isClosedForReceive) {
                        val frame = websocketSession!!.incoming.receive() as? Frame.Text ?: continue
                        val payload = json.decodeFromString<GatewayPayload>(frame.readText())
                        events.emitAsync(RawGatewayReceivedEvent(payload))
                    }
                } catch (e: SocketTimeoutException1) {
                    reconnectAfter = 3000
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                val closeReason = websocketSession!!.closeReason.await()!!
                println("Websocket closed: $closeReason")
                if (closeReason.code < 1000 || closeReason.code > 1999) {
                    reconnectAfter = Random.nextInt(1000 until 5000)
                    println("Queueing reconnect in $reconnectAfter")
                }
            }
        }
    }

    suspend fun sendToGateway(payload: GatewayPayload) {
        println("Sending: $payload")
        websocketSession!!.send(Frame.Text(json.encodeToString(payload)))
    }

    suspend fun sendIdentify() {
        val sid = sessionId
        if (sid == null)
            sendToGateway(
                GatewayOperator.IDENTIFY,
                Identify(
                    token, IdentifyConnection(System.getProperty("os.name"), "DiscordKt.kt", "DiscordKt.kt"),
                    shard = null,
                    intents = intents,
                ),
            )
        else sendToGateway(
            GatewayOperator.RESUME,
            Resume(token, sid, lastSequenceNumber ?: 0)
        )
    }

    suspend fun close(reason: Short, message: String) {
        websocketSession?.close(CloseReason(reason, message))
    }

    suspend fun reconnectNow() {
        reconnectAfter = 0
        close(4000, "Reconnecting")
    }

    suspend fun reconnectWithDelay() {
        reconnectAfter = Random.nextInt(1000 until 5000)
        close(4000, "Reconnecting")
    }

    private suspend fun setupHeartbeat(payload: GatewayPayload) {
        val hello = json.decodeFromJsonElement<Hello>(payload.data!!)
        heartbeatIntervalMs = hello.heartbeatInterval.toLong()
        scope.launch(Executors.newSingleThreadExecutor().asCoroutineDispatcher()) {
            sendHeartbeat()
            sendIdentify()
            while (true) {
                delay(heartbeatIntervalMs)
                val websocketLatencyMs = (lastHeartbeatAckAtNs - lastHeartbeatSentAtNs) / 1000
                println("Latency rn: $websocketLatencyMs")
                if (websocketLatencyMs >= 2 * heartbeatIntervalMs) {
                    close(4000, "Missed heartbeat by $websocketLatencyMs ms")
                    break
                } else
                    latencyMs = websocketLatencyMs
                sendHeartbeat()
            }
        }
    }

    private suspend fun sendHeartbeat() {
        lastHeartbeatSentAtNs = System.nanoTime()
        sendToGateway(GatewayPayload(GatewayOperator.HEARTBEAT, JsonPrimitive(lastSequenceNumber)))
    }

    private inline fun <reified T> asEvent(ev: GatewayPayload): T =
        json.decodeFromJsonElement(serializer(), ev.data!!)

    private suspend fun dispatchEvent(ev: GatewayPayload) {
        val type = GatewayEvent.valueOf(ev.type!!)
        if (type == GatewayEvent.READY) sessionId = asEvent<Ready>(ev).sessionId
        println("Received event ${type}: ${ev.data}")
    }

    fun run() = scope.launch {
        loop()
    }
}

suspend inline fun <reified T> DiscordGateway.sendToGateway(
    opcode: GatewayOperator,
    payload: T,
    s: Int? = null,
    type: String? = null
) {
    sendToGateway(GatewayPayload(opcode, json.encodeToJsonElement(serializer(), payload), s, type))
}
