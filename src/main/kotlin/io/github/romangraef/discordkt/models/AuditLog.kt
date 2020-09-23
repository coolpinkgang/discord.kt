package io.github.romangraef.discordkt.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AuditLog(
    @SerialName("webhooks")
    val webHooks: List<WebHook>,
    val users: List<User>,
    @SerialName("audit_log_entries")
    val auditLogEntries: List<Entry>,
    val integrations: List<Integration>,
) {
    @Serializable
    data class Entry (
        @SerialName("target_id")
        val targetID: String?,
        //TODO: val changes: List<Change<out Nothing>> = listOf(),
        @SerialName("user_id")
        val stringUserID: String,
        @SerialName("id")
        val stringID: String,
        @SerialName("action_type")
        val intActionType: Int,
        val options: OptionalInfo? = null,
        val reason: String? = null,
    )
    @Serializable
    data class Change<T>(
            @SerialName("new_value") val newValue: T,
            @SerialName("old_value") val oldValue: T,
            val key: String,
    )
    enum class Event(val id: Int) {
        GUILD_UPDATE(1),
        CHANNEL_CREATE(10),
        CHANNEL_UPDATE(11),
        CHANNEL_DELETE(12),
        CHANNEL_OVERWRITE_CREATE(13),
        CHANNEL_OVERWRITE_UPDATE(14),
        CHANNEL_OVERWRITE_DELETE(15),
        MEMBER_KICK(20),
        MEMBER_PRUNE(21),
        MEMBER_BAN_ADD(22),
        MEMBER_BAN_REMOVE(23),
        MEMBER_UPDATE(24),
        MEMBER_ROLE_UPDATE(25),
        MEMBER_MOVE(26),
        MEMBER_DISCONNECT(27),
        BOT_ADD(28),
        ROLE_CREATE(30),
        ROLE_UPDATE(31),
        ROLE_DELETE(32),
        INVITE_CREATE(40),
        INVITE_UPDATE(41),
        INVITE_DELETE(42),
        WEBHOOK_CREATE(50),
        WEBHOOK_UPDATE(51),
        WEBHOOK_DELETE(52),
        EMOJI_CREATE(60),
        EMOJI_UPDATE(61),
        EMOJI_DELETE(62),
        MESSAGE_DELETE(72),
        MESSAGE_BULK_DELETE(62),
        MESSAGE_PIN(74),
        MESSAGE_UNPIN(75),
        INTEGRATION_CREATE(80),
        INTEGRATION_UPDATE(81),
        INTEGRATION_DELETE(82)
    }
    @Serializable
    data class OptionalInfo(
            @SerialName("delete_member_days") val deleteMemberDays: String,
            @SerialName("members_removed") val membersRemoved: String,
            @SerialName("channel_id") val stringChannelID: String,
            @SerialName("message_id") val stringMessageID: String,
            val count: String,
            @SerialName("id") val stringID: String,
            val type: String
    )
}
