package dev.discordkt.models.guild

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildCreateBan(
    @SerialName("delete_message_days")
    val deleteMessageDays: Int? = null,
    val reason: String? = null
)
