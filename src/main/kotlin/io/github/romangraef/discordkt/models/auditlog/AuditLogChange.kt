package io.github.romangraef.discordkt.models.auditlog

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class AuditLogChange(
    val key: String,
    @SerialName("old_value")
    val oldValue: JsonElement? = null,
    @SerialName("new_value")
    val newValue: JsonElement? = null,
)
