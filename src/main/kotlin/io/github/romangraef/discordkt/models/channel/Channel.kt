@file:UseSerializers(ISO8601Serializer::class)
package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.models.user.User
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
    val type: ChannelType,
    @SerialName("guild_id")
    val guildId: Snowflake? = null,
    val position: Int? = null,
    @SerialName("permission_overwrites")
    val permissionOverwrites: List<Overwrite>? = null,
    val name: String? = null,
    val topic: String? = null,
    val nsfw: Boolean? = null,
    @SerialName("last_message_id")
    val lastMessageId: Snowflake? = null,
    val bitrate: Int? = null,
    @SerialName("user_limit")
    val userLimit: Int? = null,
    @SerialName("rate_limit_per_user")
    val rateLimitPerUser: Int? = null,
    val recipients: List<User>? = null,
    val icon: String? = null,
    val ownerId: Snowflake? = null,
    val applicationId: Snowflake? = null,
    val parentId: Snowflake? = null,
    val lastPinTimestamp: OffsetDateTime? = null,
) : SnowflakeMixin()
