package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.models.permissions.Permission
import kotlinx.serialization.Serializable

@Serializable
data class OverwriteEdit(
    val allow: Permission.BitField,
    val deny: Permission.BitField,
    val type: OverwriteType
)
