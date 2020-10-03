@file:UseSerializers(ISO8601Serializer::class)
package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.models.*
import io.github.romangraef.discordkt.models.channel.Channel
import io.github.romangraef.discordkt.models.emoji.Emoji
import io.github.romangraef.discordkt.models.serial.*
import io.github.romangraef.discordkt.models.voice.VoiceState

import java.time.OffsetDateTime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

@Serializable
data class Guild(
    override val id: Snowflake,
    val name: String,
    val icon: String?,
    val splash: String?,
    @SerialName("discovery_splash")
    val discoverySplash: String?,
    val owner: Boolean? = null,
    @SerialName("owner_id")
    val ownerId: Snowflake,
    val permissions: Permission.BitField? = null,
    val region: String,
    @SerialName("afk_channel_id")
    val afkChannelId: Snowflake?,
    @SerialName("afk_timeout")
    val afkTimeout: Int,
    @SerialName("widget_enabled")
    val widgetEnabled: Boolean? = null,
    @SerialName("widget_channel_id")
    val widgetChannelId: Snowflake? = null,
    @SerialName("verification_level")
    val verificationLevel: VerificationLevel,
    @SerialName("default_message_notifications")
    val defaultMessageNotifications: DefaultMessageNotificationsLevel,
    @SerialName("explicit_content_filter")
    val explicitContentFilter: ExplicitContentFilterLevel,
    val roles: List<Role>,
    val emojis: List<Emoji>,
    val features: List<GuildFeature>,
    @SerialName("mfa_level")
    val mfaLevel: MfaLevel,
    @SerialName("application_id")
    val applicationId: Snowflake?,
    @SerialName("system_channel_id")
    val systemChannelId: Snowflake?,
    @SerialName("system_channel_flag")
    val systemChannelFlags: SystemChannelFlag.BitField,
    @SerialName("rules_channel_id")
    val rulesChannelId: Snowflake?,
    @SerialName("joined_at")
    val joinedAt: OffsetDateTime? = null,
    val large: Boolean? = null,
    val unavailable: Boolean? = null,
    @SerialName("member_count")
    val memberCount: Int? = null,
    @SerialName("voice_states")
    val voiceStates: List<VoiceState>? = null,
    val members: List<GuildMember>? = null,
    val channels: List<Channel>? = null,
    val presences: List<PresenceUpdate>? = null,
    @SerialName("max_presences")
    val maxPresences: Int? = null,
    @SerialName("max_members")
    val maxMembers: Int? = null,
    @SerialName("vanity_url_code")
    val vanityUrlCode: String?,
    val description: String?,
    val banner: String?,
    @SerialName("premium_tier")
    val premiumTier: PremiumTier,
    @SerialName("premium_subscription_count")
    val premiumSubscriptionCount: Int? = null,
    @SerialName("preferred_locale")
    val preferredLocale: String,
    @SerialName("public_update_channel_id")
    val publicUpdateChannelId: Snowflake?,
    @SerialName("max_video_channel_users")
    val maxVideoChannelUsers: Int? = null,
    @SerialName("approximate_member_count")
    val approximateMemberCount: Int? = null,
    @SerialName("approximate_presence_count")
    val approximatePresenceCount: Int? = null
) : SnowflakeMixin()
