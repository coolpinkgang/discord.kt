@file:UseSerializers(ISO8601Serializer::class)
package dev.discordkt.models.invite

import dev.discordkt.models.channel.Channel
import dev.discordkt.models.guild.Guild
import dev.discordkt.models.serial.ISO8601Serializer
import dev.discordkt.models.user.User

import java.time.OffsetDateTime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

@Serializable
data class InviteWithMetadata(
    val code: String,
    val guild: Guild? = null,
    val channel: Channel,
    val inviter: User? = null,
    @SerialName("target_user")
    val targetUser: User? = null,
    @SerialName("target_user_type")
    val targetUserType: TargetUserType? = null,
    @SerialName("approximate_presence_count")
    val approximatePresenceCount: Int? = null,
    @SerialName("approximate_member_count")
    val approximateMemberCount: Int? = null,
    val uses: Int,
    @SerialName("max_uses")
    val maxUses: Int,
    @SerialName("max_age")
    val maxAge: Int,
    val temporary: Boolean,
    @SerialName("created_at")
    val createdAt: OffsetDateTime
)
