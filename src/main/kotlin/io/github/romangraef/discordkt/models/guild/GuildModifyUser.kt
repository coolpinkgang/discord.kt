package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.models.serial.Snowflake
import kotlinx.serialization.Serializable

@Serializable
data class GuildModifyUser(
    val nick: String? = null,
    val roles: List<Snowflake>? = null,
    val mute: Boolean? = null,
    val deaf: Boolean? = null,
    val channelId: Snowflake? = null
)
