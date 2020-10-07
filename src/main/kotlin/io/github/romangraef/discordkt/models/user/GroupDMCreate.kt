package io.github.romangraef.discordkt.models.user

import io.github.romangraef.discordkt.models.serial.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupDMCreate(
    @SerialName("access_tokens")
    val accessTokens: List<String>,
    val nicks: Map<Snowflake, String>
)
