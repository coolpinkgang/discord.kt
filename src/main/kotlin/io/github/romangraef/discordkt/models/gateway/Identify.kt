package io.github.romangraef.discordkt.models.gateway

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Identify(
    val token: String,
    val properties: IdentifyConnection,
    val compress: Boolean? = null,
    @SerialName("large_threshold")
    val largeThreshold: Int? = null,
    val shard: Pair<Int, Int>? = null,
    val presence: GatewayStatusUpdate? = null,
    @SerialName("guild_subscriptions")
    val guildSubscriptions: Boolean? = null,
    val intents: Intent.BitField? = null
)

