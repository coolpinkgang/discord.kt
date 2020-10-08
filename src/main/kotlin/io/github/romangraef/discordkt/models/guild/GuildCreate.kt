package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.snowflake.Snowflake
import io.github.romangraef.discordkt.models.channel.Channel
import io.github.romangraef.discordkt.models.permissions.Role
import io.github.romangraef.discordkt.models.serial.ImageData

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildCreate(
    val name: String,
    val region: String? = null,
    val icon: ImageData? = null,
    @SerialName("verification_level")
    val verificationLevel: VerificationLevel? = null,
    @SerialName("default_message_notifications")
    val defaultMessageNotificationsLevel: DefaultMessageNotificationsLevel? = null,
    @SerialName("explicit_content_filter")
    val explicitContentFilterLevel: ExplicitContentFilterLevel? = null,
    val roles: List<Role>? = null,
    val channels: List<Channel>? = null,
    @SerialName("afk_channel_id")
    val afkChannelId: Snowflake? = null,
    @SerialName("afk_timeout")
    val afkTimeout: Int? = null,
    @SerialName("system_channel_id")
    val systemChannelId: Snowflake? = null
)
