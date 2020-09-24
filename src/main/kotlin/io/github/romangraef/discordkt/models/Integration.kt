package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.serial.SnowflakeMixin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Integration(
    override val id: Snowflake,
    val name: String,
    val type: String,
    val enabled: Boolean,
    val syncing: Boolean,
    @SerialName("role_id")
    val roleId: Snowflake,
    @SerialName("enable_emoticons")
    val enableEmoticons: Boolean = false,
    @SerialName("expire_behavior")
    val expireBehavior: ExpireBehavior,
    @SerialName("expire_grace_period")
    val expireGracePeriod: Int,
    val user: User,
    val account: Account,
    @SerialName("synced_at")
    val stringSyncedAt: String //TODO: ISO8601 timestamp!
) : SnowflakeMixin() {
    enum class ExpireBehavior {
        REMOVE_ROLE, KICK
    }
}
