package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.snowflake.Snowflake

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

object GuildBeginPrune {
    @Serializable
    data class In(
        val pruned: Int
    )
    @Serializable
    data class Out(
        val days: Int,
        @SerialName("compute_prune_count")
        val computePruneCount: Boolean,
        @SerialName("include_roles")
        val includeRoles: List<Snowflake>
    )
}
