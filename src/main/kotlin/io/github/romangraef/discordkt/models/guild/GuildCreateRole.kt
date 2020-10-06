package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.models.Color
import io.github.romangraef.discordkt.models.permissions.Permission
import kotlinx.serialization.Serializable

@Serializable
data class GuildCreateRole(
    val name: String,
    val permissions: Permission.BitField,
    val color: Color,
    val hoist: Boolean,
    val mentionable: Boolean
)
