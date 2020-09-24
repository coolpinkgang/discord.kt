package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.serial.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PermissionOverwriteReceiving(
    val id: Snowflake,
    val type: PermissionOverwriteType,
    @Deprecated("Legacy permission bit set", ReplaceWith("allowNew"))
    val allow: Int,
    @SerialName("allow_new")
    val allowNew: String,
    @Deprecated("Legacy permission bit set", ReplaceWith("denyNew"))
    val deny: Int,
    @SerialName("deny_new")
    val denyNew: String
)

@Serializable
data class PermissionOverwriteSending(
    val id: Snowflake,
    val type: PermissionOverwriteType,
    val allow: String, //TODO: or INT
    val deny: String, //TODO: or INT
)

enum class PermissionOverwriteType {
    role,
    member
}
