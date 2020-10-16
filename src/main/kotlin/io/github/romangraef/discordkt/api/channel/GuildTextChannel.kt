package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.snowflake.Snowflake

class GuildTextChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake
) : GuildTextLikeChannel {
    val ratePerUserLimit: Int = backing.rateLimitPerUser!!
    override suspend fun changeType(): GuildNewsChannel = TODO()
    fun changeRatePerUserLimit(limit: Int): Unit = TODO()
}
