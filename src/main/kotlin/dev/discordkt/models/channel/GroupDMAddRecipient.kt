package dev.discordkt.models.channel

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GroupDMAddRecipient(
    @SerialName("access_token")
    val accessToken: String,
    val nick: String
)
