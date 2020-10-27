package dev.discordkt.models.emoji

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.snowflake.SnowflakeMixin
import dev.discordkt.models.user.User

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Emoji(
    override val id: Snowflake,
    val name: String? = null,
    val roles: List<Snowflake>? = null,
    val user: User? = null,
    @SerialName("require_colons")
    val requireColons: Boolean? = null,
    val managed: Boolean? = null,
    val animated: Boolean? = null,
    val available: Boolean? = null,
) : SnowflakeMixin
