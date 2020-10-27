package dev.discordkt.models

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

inline fun <reified T> String.deserializes(condition: (T) -> Unit) {
    condition(Json { ignoreUnknownKeys = true }.decodeFromString(this))
}
