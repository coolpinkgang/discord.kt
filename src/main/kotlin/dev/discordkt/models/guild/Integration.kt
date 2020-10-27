@file:UseSerializers(ISO8601Serializer::class)
package dev.discordkt.models.guild

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.snowflake.SnowflakeMixin
import dev.discordkt.models.user.User
import dev.discordkt.models.serial.ISO8601Serializer

import java.time.OffsetDateTime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

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
    val expireBehavior: IntegrationExpireBehavior,
    @SerialName("expire_grace_period")
    val expireGracePeriod: Int,
    val user: User,
    val account: IntegrationAccount,
    @SerialName("synced_at")
    val stringSyncedAt: OffsetDateTime
) : SnowflakeMixin
