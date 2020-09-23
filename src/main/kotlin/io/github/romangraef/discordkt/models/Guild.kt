package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.serial.SnowflakeMixin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Guild(
    override val id: Snowflake,
    val name: String,
    val icon: String?,
    val splash: String?,
    @SerialName("discovery_splash") val discoverySplash: String?,
    val owner: Boolean = false,
    @SerialName("owner_id") val stringOwnerId: String,
    val permissions: Int = 0,
    val region: String,
    @SerialName("afk_channel_id") val stringAfkChannelId: String?,
    @SerialName("afk_timeout") val afkTimeout: Int,
    @Deprecated("deprecated", ReplaceWith("widgetEnabled"))
    @SerialName("embed_enabled") val embedEnabled: Boolean = false,
    @Deprecated("deprecated", ReplaceWith("stringWidgetChannelId"))
    @SerialName("embed_channel_id") val stringEmbedChannelId: String? = null,
    @SerialName("verification_level") val verificationLevel: Int,
    @SerialName("default_message_notifications") val defaultMessageNotifications: Int,
    @SerialName("explicit_content_filter") val explicitContentFilter: Int,
    val roles: List<Role>,
    val emojis: List<Emoji>,
    val features: List<Features>,
    @SerialName("mfa_level") val mfaLevel: Int,
    @SerialName("application_id") val stringApplicationId: String?,
    @SerialName("widget_enabled") val widgetEnabled: Boolean = false,
    @SerialName("widget_channel_id") val stringWidgetChannelId: String? = null,
    @SerialName("system_channel_id") val stringSystemChannelId: String? = null,
) : SnowflakeMixin() {
    enum class Features {
        //TODO: implement
    }
}
