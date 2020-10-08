package io.github.romangraef.discordkt.models.gateway

import io.github.romangraef.discordkt.snowflake.Snowflake
import io.github.romangraef.discordkt.models.guild.GuildMember

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildMemberChunk(
    @SerialName("guild_id")
    val guildId: Snowflake,
    val members: List<GuildMember>,
    @SerialName("chunk_index")
    val chunkIndex: Int,
    @SerialName("chunk_count")
    val chunkCount: Int,
    @SerialName("not_found")
    val notFound: List<Snowflake>? = null,
    val presences: List<PresenceUpdate>? = null,
    val nonce: String? = null
)
