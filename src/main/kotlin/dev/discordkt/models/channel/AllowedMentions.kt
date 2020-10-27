package dev.discordkt.models.channel

import dev.discordkt.snowflake.Snowflake

import kotlinx.serialization.Serializable

@Serializable
data class AllowedMentions(
    val parse: List<AllowedMentionType>,
    val roles: List<Snowflake>,
    val users: List<Snowflake>
)
