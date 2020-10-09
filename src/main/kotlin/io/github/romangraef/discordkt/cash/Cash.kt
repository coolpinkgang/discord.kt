package io.github.romangraef.discordkt.cash

import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.api.User
import io.github.romangraef.discordkt.api.channel.BaseChannel
import io.github.romangraef.discordkt.api.channel.GuildChannel
import io.github.romangraef.discordkt.api.guild.Guild
import io.github.romangraef.discordkt.api.guild.Member
import io.github.romangraef.discordkt.http.routes.GuildRoutes
import io.github.romangraef.discordkt.http.routes.UserRoutes
import io.github.romangraef.discordkt.snowflake.Snowflake

class Cash(val discordKt: DiscordKt) {
    val guilds: MutableList<Guild> = mutableListOf()
    val channels: MutableList<BaseChannel> = mutableListOf()

    fun updateGuild(guild: Guild) = guilds.replaceAll { if (it.id == guild.id) guild else it }
    fun updateChannel(channel: BaseChannel) = channels.replaceAll { if (it.id == channel.id) channel else it }

    suspend fun load() = loadUserGuildsAsSnowflake().forEach { snowflake ->
        val channels = discordKt.routeExecutor.execute(GuildRoutes.GET_GUILD_CHANNELS(snowflake))
            .map { BaseChannel.of(it) }
            .also { channels.addAll(it) }
            .map { it as GuildChannel }
        discordKt.routeExecutor.execute(GuildRoutes.GET_GUILD(snowflake))
            .let { Guild.of(it, channels) }
            .let { guilds.add(it) }
    }

    private suspend fun loadUserGuildsAsSnowflake(): List<Snowflake> {
        val snowflakes = mutableListOf<Snowflake>()
        var userGuildsPart: List<Snowflake>
        do {
            userGuildsPart = discordKt.routeExecutor
                .execute(UserRoutes.GET_CURRENT_USER_GUILDS(null, snowflakes.lastOrNull()))
                .map { it.asSnowflake }
            snowflakes.addAll(userGuildsPart)
        } while (userGuildsPart.size == 100)
        return snowflakes
    }
}
