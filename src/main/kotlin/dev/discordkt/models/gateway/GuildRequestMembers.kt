package dev.discordkt.models.gateway

import dev.discordkt.snowflake.Snowflake

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildRequestMembers(
    @SerialName("guild_id")
    val guildId: Snowflake, // TODO: or List<Snowflake>
    val query: String? = null,
    val limit: Int,
    val presences: Boolean? = null,
    @SerialName("user_ids")
    val userIds: Snowflake? = null, // TODO: or List<Snowflake>
    val nonce: String? = null
)
