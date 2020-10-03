package io.github.romangraef.discordkt.models.auditlog

import io.github.romangraef.discordkt.models.guild.Integration
import io.github.romangraef.discordkt.models.User
import io.github.romangraef.discordkt.models.Webhook

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuditLog(
    @SerialName("webhooks")
    val webHooks: List<Webhook>,
    val users: List<User>,
    @SerialName("audit_log_entries")
    val auditLogEntries: List<AuditLogEntry>,
    val integrations: List<Integration>, // TODO partial integrations
)
