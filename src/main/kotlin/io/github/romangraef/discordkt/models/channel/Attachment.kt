package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.serial.SnowflakeMixin
import kotlinx.serialization.SerialName

import kotlinx.serialization.Serializable

@Serializable
data class Attachment(
    override val id: Snowflake,
    val filename: String,
    val size: Int,
    val url: String,
    @SerialName("proxy_url")
    val proxyUrl: String,
    val height: Int?,
    val width: Int?
) : SnowflakeMixin()
