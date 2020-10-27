package dev.discordkt.models.auditlog

import dev.discordkt.models.guild.Integration
import dev.discordkt.models.user.User
import dev.discordkt.models.webhook.Webhook

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
