package io.github.romangraef.discordkt.http.routes

import io.github.romangraef.discordkt.models.auditlog.AuditLog
import io.github.romangraef.discordkt.models.serial.Snowflake

object AuditLog {
    fun GET_AUDIT_LOG(guildId: Snowflake) = GET<AuditLog>("/guilds/$guildId/audit-logs")
}
