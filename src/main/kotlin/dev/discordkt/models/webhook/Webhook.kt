package dev.discordkt.models.webhook

import dev.discordkt.models.user.User
import dev.discordkt.snowflake.Snowflake
import dev.discordkt.snowflake.SnowflakeMixin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Webhook(
    override val id: Snowflake,
    @SerialName("type")
    val type: WebhookType,
    @SerialName("guild_id")
    val guildId: Snowflake? = null,
    @SerialName("channel_id")
    val channelId: Snowflake,
    val user: User? = null,
    val name: String?,
    val avatar: String?,
    val token: String? = null
) : SnowflakeMixin
