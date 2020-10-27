package dev.discordkt.http.routes

import dev.discordkt.models.auditlog.AuditLog
import dev.discordkt.snowflake.Snowflake

object AuditLogRoutes {
    fun GET_AUDIT_LOG(guildId: Snowflake) = GET<AuditLog>("/guilds/$guildId/audit-logs")
}
