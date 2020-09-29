@file:UseSerializers(ISO8601Serializer::class)
package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.serial.ISO8601Serializer
import io.github.romangraef.discordkt.models.serial.Snowflake
import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers
import java.time.OffsetDateTime

@Serializable
data class Member(
    val user: User? = null,
    val nick: String?,
    val roles: List<Snowflake>,
    @SerialName("joined_at")
    val joinedAt: OffsetDateTime,
    @SerialName("premium_since")
    val premiumSince: OffsetDateTime = OffsetDateTime.MIN,
    val deaf: Boolean,
    val mute: Boolean
)
