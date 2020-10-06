package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.models.serial.ImageData
import io.github.romangraef.discordkt.models.serial.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.util.*

@Serializable
data class GuildModify(
    val name: String,
    val region: String? = null,
    @SerialName("verification_level")
    val verificationLevel: VerificationLevel? = null,
    @SerialName("default_message_notification")
    val defaultMessageNotifications: DefaultMessageNotificationsLevel? = null,
    @SerialName("explicit_content_filter")
    val explicitContentFilterLevel: ExplicitContentFilterLevel? = null,
    @SerialName("afk_channel_id")
    val afkChannelId: Snowflake? = null,
    @SerialName("afk_timeout")
    val afkTimeout: Int? = null,
    val icon: ImageData? = null,
    @SerialName("owner_id")
    val ownerId: Snowflake? = null,
    val splash: ImageData? = null,
    val banner: ImageData? = null,
    @SerialName("system_channel_id")
    val systemChannelId: Snowflake? = null,
    @SerialName("rules_channel_id")
    val rulesChannelId: Snowflake? = null,
    @SerialName("public_updates_channel_id")
    val publicUpdatesChannelId: Snowflake? = null,
    @SerialName("preferred_locale")
    val preferredLocale: String? = null
)
