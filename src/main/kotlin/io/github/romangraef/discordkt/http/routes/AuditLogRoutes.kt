package io.github.romangraef.discordkt.http.routes

import io.github.romangraef.discordkt.models.auditlog.AuditLog

import io.github.romangraef.discordkt.snowflake.Snowflake

object AuditLogRoutes {
    fun GET_AUDIT_LOG(guildId: Snowflake) = GET<AuditLog>("/guilds/$guildId/audit-logs")
}
