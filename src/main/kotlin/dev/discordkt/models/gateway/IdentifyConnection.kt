package dev.discordkt.models.gateway

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class IdentifyConnection(
    @SerialName("\$os")
    val os: String,
    @SerialName("\$browser")
    val browser: String,
    @SerialName("\$device")
    val device: String
)
