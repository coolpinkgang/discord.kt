package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.snowflake.Snowflake
import io.github.romangraef.discordkt.models.permissions.Permission

import kotlinx.serialization.Serializable
@Serializable
data class Overwrite(
    val id: Snowflake,
    val type: OverwriteType,
    val allow: Permission.BitField,
    val deny: Permission.BitField,
)
