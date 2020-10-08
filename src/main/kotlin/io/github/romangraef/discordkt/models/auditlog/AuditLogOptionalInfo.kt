package io.github.romangraef.discordkt.models.auditlog

import io.github.romangraef.discordkt.snowflake.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuditLogOptionalInfo(
	@SerialName("delete_member_days")
    val deleteMemberDays: String? = null,
	@SerialName("members_removed")
    val membersRemoved: String? = null,
	@SerialName("channel_id")
    val channelId: Snowflake? = null,
	@SerialName("message_id")
    val messageId: Snowflake? = null,
	val count: String,
	val id: Snowflake? = null,
	val type: String? = null
)
