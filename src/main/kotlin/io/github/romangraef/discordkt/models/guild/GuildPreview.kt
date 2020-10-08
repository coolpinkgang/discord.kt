package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.snowflake.Snowflake
import io.github.romangraef.discordkt.snowflake.SnowflakeMixin
import io.github.romangraef.discordkt.models.emoji.Emoji

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
