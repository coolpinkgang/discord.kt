@file:UseSerializers(ISO8601Serializer::class)
package io.github.romangraef.discordkt.models.gateway

import io.github.romangraef.discordkt.models.invite.TargetUserType
import io.github.romangraef.discordkt.models.serial.ISO8601Serializer
import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.user.User

import java.time.OffsetDateTime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

@Serializable
data class InviteCreate(
    @SerialName("channel_id")
    val channelId: Snowflake,
    val code: String,
    @SerialName("created_at")
    val createdAt: OffsetDateTime,
    @SerialName("guild_id")
    val guildId: Snowflake? = null,
    val inviter: User? = null,
    @SerialName("max_age")
    val maxAge: Int,
    @SerialName("max_uses")
    val maxUses: Int,
    @SerialName("target_user")
    val targetUser: User? = null,
    @SerialName("target_user_type")
    val targetUserType: TargetUserType? = null,
    val temporary: Boolean,
    val uses: Int
)
