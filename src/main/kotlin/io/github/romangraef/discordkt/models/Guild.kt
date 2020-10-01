@file:UseSerializers(ISO8601Serializer::class)
package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.emoji.Emoji
import io.github.romangraef.discordkt.models.serial.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.OffsetDateTime

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
    @Deprecated("deprecated", ReplaceWith("permissionsNew"))
    val permissions: Int = 0,
    @SerialName("permissions_new")
    val permissionsNew: Permissions = Permissions(emptyList()),
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
    val verificationLevel: VerificationLevel,
    @SerialName("default_message_notifications")
    val defaultMessageNotifications: DefaultMessageNotificationsLevel,
    @SerialName("explicit_content_filter")
    val explicitContentFilter: ExplicitContentFilterLevel,
    val roles: List<Role>,
    val emojis: List<Emoji>,
    val features: List<Feature>,
    @SerialName("mfa_level")
    val mfaLevel: MfaLevel,
    @SerialName("application_id")
    val applicationId: Snowflake?,
    @SerialName("widget_enabled")
    val widgetEnabled: Boolean = false,
    @SerialName("widget_channel_id")
    val widgetChannelId: Snowflake? = null,
    @SerialName("system_channel_id")
    val systemChannelId: Snowflake?,
    @SerialName("system_channel_flag")
    val systemChannelFlags: SystemChannelFlags,
    @SerialName("rules_channel_id")
    val rulesChannelId: Snowflake?,
    @SerialName("joined_at")
    val joinedAt: OffsetDateTime = OffsetDateTime.MIN,
    val large: Boolean = false,
    val unavailable: Boolean = false,
    @SerialName("member_count")
    val memberCount: Int = 0,
    @SerialName("voice_states")
    val voiceStates: List<VoiceState> = emptyList(),
    val members: List<Member> = emptyList(),
    val channels: List<Channel> = emptyList(),
    val presences: List<PresenceUpdate> = emptyList(),
    @SerialName("max_presences")
    val maxPresences: Int? = null,
    @SerialName("max_members")
    val maxMembers: Int = 0,
    @SerialName("vanity_url_code")
    val vanityUrlCode: String?,
    val description: String?,
    val banner: String?,
    @SerialName("premium_tier")
    val premiumTier: PremiumTier,
    @SerialName("premium_subscription_count")
    val premiumSubscriptionCount: Int = 0,
    @SerialName("preferred_locale")
    val preferredLocale: String,
    @SerialName("public_update_channel_id")
    val publicUpdateChannelId: Snowflake?,
    @SerialName("max_video_channel_users")
    val maxVideoChannelUsers: Int = 0,
    @SerialName("approximate_member_count")
    val approximateMemberCount: Int = 0,
    @SerialName("approximate_presence_count")
    val approximatePresenceCount: Int = 0
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
    @Serializable(with = SystemChannelFlags.Serializer::class)
    class SystemChannelFlags(val backingList: List<SystemChannelFlag>) : List<SystemChannelFlag> by backingList{
        class Serializer : FlagsSerializer<SystemChannelFlag, SystemChannelFlags>(SystemChannelFlag::values, ::SystemChannelFlags)
    }
    enum class SystemChannelFlag {
        SUPPRESS_JOIN_NOTIFICATIONS,
        SUPPRESS_PREMIUM_SUBSCRIPTIONS
    }
    enum class PremiumTier {
        NONE,
        TIER_1,
        TIER_2,
        TIER_3
    }
    enum class VerificationLevel {
        NONE,
        LOW,
        MEDIUM,
        HIGH,
        VERY_HIGH
    }
    enum class MfaLevel {
        NONE,
        ELEVATED
    }
    enum class ExplicitContentFilterLevel {
        DISABLED,
        MEMBERS_WITHOUT_ROLES,
        ALL_MEMBERS
    }
    enum class DefaultMessageNotificationsLevel {
        ALL_MESSAGES,
        ONLY_MENTIONS
    }
}
