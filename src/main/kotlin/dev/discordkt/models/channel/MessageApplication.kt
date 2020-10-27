package dev.discordkt.models.channel

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.snowflake.SnowflakeMixin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MessageApplication(
    override val id: Snowflake,
    @SerialName("cover_image")
    val coverImage: String? = null,
    val description: String,
    val icon: String? = null,
    val name: String
) : SnowflakeMixin
