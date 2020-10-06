package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.models.serial.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildAddMember(
    @SerialName("access_token")
    val accessToken: String,
    val nick: String? = null,
    val roles: List<Snowflake>? = null,
    val mute: Boolean? = null,
    val deaf: Boolean? = null
)
