@file:UseSerializers(ISO8601Serializer::class)
package dev.discordkt.models.guild

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.models.user.User
import dev.discordkt.models.serial.ISO8601Serializer

import java.time.OffsetDateTime

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

@Serializable
data class GuildMember(
    val user: User? = null,
    val nick: String?,
    val roles: List<Snowflake>,
    @SerialName("joined_at")
    val joinedAt: OffsetDateTime,
    @SerialName("premium_since")
    val premiumSince: OffsetDateTime? = null,
    val deaf: Boolean,
    val mute: Boolean
)
