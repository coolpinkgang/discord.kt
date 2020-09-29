@file:UseSerializers(ISO8601Serializer::class)
package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.serial.ISO8601Serializer
import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.serial.SnowflakeMixin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.OffsetDateTime

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
    val stringSyncedAt: OffsetDateTime
) : SnowflakeMixin() {
    enum class ExpireBehavior {
        REMOVE_ROLE, KICK
    }
}
