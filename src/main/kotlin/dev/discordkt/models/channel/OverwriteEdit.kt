package dev.discordkt.models.channel

import dev.discordkt.models.permissions.Permission

import kotlinx.serialization.Serializable

@Serializable
data class OverwriteEdit(
    val allow: Permission.BitField,
    val deny: Permission.BitField,
    val type: OverwriteType
)
