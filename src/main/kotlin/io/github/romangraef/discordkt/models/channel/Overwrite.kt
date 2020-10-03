package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.models.serial.Snowflake
import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable
@Serializable
data class Overwrite(
    val id: Snowflake,
    val type: OverwriteType,
    @SerialName("allow_new")
    val allow: String,
    @SerialName("deny_new")
    val deny: String,
    @SerialName("allow")
    @Deprecated("deprecated", ReplaceWith("allow"))
    val oldAllow: Int = 0,
    @SerialName("deny")
    @Deprecated("deprecated", ReplaceWith("deny"))
    val oldDeny: Int = 0,
)
