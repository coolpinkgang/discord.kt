package io.github.romangraef.discordkt.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Integration(
    @SerialName("id") val stringID: String,
    val name: String,
    val type: String,
    val enabled: Boolean,
    val syncing: Boolean,
    @SerialName("role_id") val stringRoleId: String,
    @SerialName("enable_emoticons") val enableEmoticons: Boolean = false,
    @SerialName("expire_behavior") val intExpireBehavior: Int,
    @SerialName("expire_grace_period") val expireGracePeriod: Int,
    val user: User,
    val account: Account,
    @SerialName("synced_at") val stringSyncedAt: String
) {
    enum class ExpireBehavior {
        REMOVE_ROLE, KICK
    }
}
