package io.github.romangraef.discordkt.models.invite

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class InviteCreate(
    @SerialName("max_age")
    val maxAge: Int = 86400,
    @SerialName("max_uses")
    val maxUses: Int = 0,
    val temporary: Boolean = false,
    val unique: Boolean = false,
    @SerialName("target_user")
    val targetUser: String? = null,
    val targetUserType: TargetUserType? = null
)
