@file:UseSerializers(ISO8601Serializer::class)
package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.serial.ISO8601Serializer
import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.serial.SnowflakeMixin
import java.time.OffsetDateTime
import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

@Serializable
data class Channel(
    override val id: Snowflake,
    val type: Type,
    @SerialName("guild_id")
    val guildId: Snowflake,
    val position: Int,
    @SerialName("permission_overwrites")
    val permissionOverwrites: List<PermissionOverwriteReceiving>,
    val name: String,
    val topic: String?,
    val nsfw: Boolean,
    @SerialName("last_message_id")
    val lastMessageId: Snowflake?,
    val bitrate: Int,
    @SerialName("user_limit")
    val userLimit: Int,
    @SerialName("rate_limit_per_user")
    val rateLimitPerUser: Int,
    val recipients: List<User>,
    val icon: String?,
    val ownerId: Snowflake,
    val applicationId: Snowflake,
    val parentId: Snowflake,
    val lastPinTimestamp: OffsetDateTime, //TODO: ISO8601 timestamp
) : SnowflakeMixin() {
    enum class Type {
        GUILD_TEXT,
        DM,
        GUILD_VOICE,
        GROUP_DM,
        GUILD_CATEGORY,
        GUILD_NEWS,
        GUILD_STORE
    }
}
