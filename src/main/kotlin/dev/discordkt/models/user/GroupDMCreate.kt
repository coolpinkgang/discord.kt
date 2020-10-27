package dev.discordkt.models.user

import dev.discordkt.snowflake.Snowflake

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupDMCreate(
    @SerialName("access_tokens")
    val accessTokens: List<String>,
    val nicks: Map<Snowflake, String>
)
