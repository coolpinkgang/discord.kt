package io.github.romangraef.discordkt.models.gateway

import io.github.romangraef.discordkt.snowflake.Snowflake

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Activity(
    val name: String,
    val type: ActivityType,
    val url: String? = null,
    @SerialName("created_at")
    val createdAt: Long,
    val timestamps: ActivityTimestamps? = null,
    @SerialName("application_id")
    val applicationId: Snowflake? = null,
    val details: String? = null,
    val state: String? = null,
    val emoji: ActivityEmoji? = null,
    val party: ActivityParty? = null,
    val assets: ActivityAssets? = null,
    val secrets: ActivitySecrets? = null,
    val instance: Boolean? = null,
    val flags: ActivityFlag.BitField? = null
)
