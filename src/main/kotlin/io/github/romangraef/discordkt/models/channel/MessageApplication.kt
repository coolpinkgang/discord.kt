package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.serial.SnowflakeMixin
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
) : SnowflakeMixin()