package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.serial.SnowflakeMixin
import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable

@Serializable
data class Channel(
    override val id: Snowflake,
    val type: Type,
    @SerialName("guild_id")
    val guildId: Snowflake,
    val position: Int,
    @SerialName("permission_overwrites")
    val permissionOverwrites: List<PermissionOverwriteReceiving>
) : SnowflakeMixin() {
    enum class Type {
        GUILD_TEXT,
        DM,
        GUILD_VOICE,
        GROUP_DM,
        GUILD_CATEGORY,
        GUILD_NEWS,
        GUILD_STORE
    }
}
