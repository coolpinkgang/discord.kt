package dev.discordkt.models.invite

import dev.discordkt.models.user.User
import dev.discordkt.models.channel.Channel
import dev.discordkt.models.guild.Guild

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Invite(
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
    val approximateMemberCount: Int? = null
)
