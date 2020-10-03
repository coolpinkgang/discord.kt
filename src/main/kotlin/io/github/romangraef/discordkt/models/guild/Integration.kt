@file:UseSerializers(ISO8601Serializer::class)
package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.models.user.User
import io.github.romangraef.discordkt.models.serial.ISO8601Serializer
import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.serial.SnowflakeMixin

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
) : SnowflakeMixin()
