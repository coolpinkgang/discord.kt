package io.github.romangraef.discordkt.models.auditlog

import io.github.romangraef.discordkt.snowflake.Snowflake
import io.github.romangraef.discordkt.snowflake.SnowflakeMixin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuditLogEntry (
    @SerialName("target_id")
    val targetId: String?,
    val changes: List<AuditLogChange> = listOf(),
    @SerialName("user_id")
    val userId: Snowflake,
    override val id: Snowflake,
    @SerialName("action_type")
    val actionType: AuditLogEvent,
    val options: AuditLogOptionalInfo? = null,
    val reason: String? = null,
) : SnowflakeMixin
