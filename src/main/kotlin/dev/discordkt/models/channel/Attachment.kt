package dev.discordkt.models.channel

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.snowflake.SnowflakeMixin

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
) : SnowflakeMixin
