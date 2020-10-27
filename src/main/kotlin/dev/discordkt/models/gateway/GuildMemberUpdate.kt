@file:UseSerializers(ISO8601Serializer::class)
package dev.discordkt.models.gateway

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.models.serial.ISO8601Serializer
import dev.discordkt.models.user.User

import java.time.OffsetDateTime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

@Serializable
data class GuildMemberUpdate(
    @SerialName("guild_id")
    val guildId: Snowflake,
    val roles: List<Snowflake>,
    val user: User,
    val nick: String? = null,
    @SerialName("joined_at")
    val joinedAt: OffsetDateTime,
    @SerialName("premium_since")
    val premiumSince: OffsetDateTime? = null
)
