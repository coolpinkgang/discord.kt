package dev.discordkt.models.guild

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.snowflake.SnowflakeMixin
import dev.discordkt.models.emoji.Emoji

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildPreview(
    override val id: Snowflake,
    val name: String,
    val icon: String?,
    val splash: String?,
    @SerialName("discovery_splash")
    val discoverySplash: String?,
    val emojis: List<Emoji>,
    val features: List<GuildFeature>,
    @SerialName("approximate_member_count")
    val approximateMemberCount: Int,
    @SerialName("approximate_presence_count")
    val approximatePresenceCount: Int,
    val description: String?
) : SnowflakeMixin
