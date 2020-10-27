package dev.discordkt.models.guild

import dev.discordkt.models.Color
import dev.discordkt.models.permissions.Permission
import kotlinx.serialization.Serializable

@Serializable
data class GuildModifyRole(
    val name: String,
    val permissions: Permission.BitField,
    val color: Color,
    val hoist: Boolean,
    val mentionable: Boolean
)
