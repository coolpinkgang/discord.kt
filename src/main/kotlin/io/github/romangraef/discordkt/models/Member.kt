package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.serial.Snowflake
import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable

@Serializable
data class Member(
    val user: User? = null,
    val nick: String?,
    val roles: List<Snowflake>,
    @SerialName("joined_at")
    val joinedAt: String, //TODO: ISO8601 timestamp
    @SerialName("premium_since")
    val premiumSince: String? = null, //TODO: ISO8601 timestamp
    val deaf: Boolean,
    val mute: Boolean
)
