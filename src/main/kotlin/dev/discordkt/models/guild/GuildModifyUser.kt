package dev.discordkt.models.guild

import dev.discordkt.snowflake.Snowflake

import kotlinx.serialization.Serializable

@Serializable
data class GuildModifyUser(
    val nick: String? = null,
    val roles: List<Snowflake>? = null,
    val mute: Boolean? = null,
    val deaf: Boolean? = null,
    val channelId: Snowflake? = null
)
