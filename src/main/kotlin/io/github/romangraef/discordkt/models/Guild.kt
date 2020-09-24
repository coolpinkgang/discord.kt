package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.serial.NameEnumSerializer
import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.serial.SnowflakeMixin
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
data class Guild(
    override val id: Snowflake,
    val name: String,
    val icon: String?,
    val splash: String?,
    @SerialName("discovery_splash")
    val discoverySplash: String?,
    val owner: Boolean = false,
    @SerialName("owner_id")
    val ownerId: Snowflake,
    val permissions: Int = 0,
    val region: String,
    @SerialName("afk_channel_id")
    val afkChannelId: Snowflake?,
    @SerialName("afk_timeout")
    val afkTimeout: Int,
    @Deprecated("deprecated", ReplaceWith("widgetEnabled"))
    @SerialName("embed_enabled")
    val embedEnabled: Boolean = false,
    @Deprecated("deprecated", ReplaceWith("widgetChannelId"))
    @SerialName("embed_channel_id")
    val embedChannelId: Snowflake? = null,
    @SerialName("verification_level")
    val verificationLevel: Int,
    @SerialName("default_message_notifications")
    val defaultMessageNotifications: Int,
    @SerialName("explicit_content_filter")
    val explicitContentFilter: Int,
    val roles: List<Role>,
    val emojis: List<Emoji>,
    val features: List<Feature>,
    @SerialName("mfa_level")
    val mfaLevel: Int,
    @SerialName("application_id")
    val applicationId: Snowflake?,
    @SerialName("widget_enabled")
    val widgetEnabled: Boolean = false,
    @SerialName("widget_channel_id")
    val widgetChannelId: Snowflake? = null,
    @SerialName("system_channel_id")
    val systemChannelId: Snowflake? = null,
) : SnowflakeMixin() {
    @Serializable(with = Feature.Serializer::class)
    enum class Feature {
        INVITE_SPLASH,
        VIP_REGIONS,
        VANITY_URL,
        VERIFIED,
        PARTNERED,
        PUBLIC,
        COMMERCE,
        NEWS,
        DISCOVERABLE,
        FEATURABLE,
        ANIMATED_ICON,
        BANNER,
        PUBLIC_DISABLED,
        WELCOME_SCREEN_ENABLED;
        class Serializer : NameEnumSerializer<Feature>("Feature", Feature::valueOf)
    }
}
