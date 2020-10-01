package io.github.romangraef.discordkt.models.channel

import io.github.romangraef.discordkt.models.serial.Snowflake
import kotlinx.serialization.Serializable

@Serializable
data class AllowedMentions(
    val parse: List<AllowedMentionType>,
    val roles: List<Snowflake>,
    val users: List<Snowflake>
)
