package dev.discordkt.models.channel

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.models.permissions.Permission

import kotlinx.serialization.Serializable

@Serializable
data class Overwrite(
    val id: Snowflake,
    val type: OverwriteType,
    val allow: Permission.BitField,
    val deny: Permission.BitField,
)
