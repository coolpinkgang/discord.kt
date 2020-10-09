package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.api.message.Message
import io.github.romangraef.discordkt.api.guild.Guild
import io.github.romangraef.discordkt.api.permission.PermissionOverwrite

import io.github.romangraef.discordkt.http.routes.ChannelRoutes

import io.github.romangraef.discordkt.snowflake.Snowflake

import kotlinx.coroutines.async

class GuildNewsChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake,
    override val name: String,
    override val topic: String,
    override val guild: Guild,
    override val position: Int,
    override val permissionOverwrites: List<PermissionOverwrite>,
    override val nsfw: Boolean,
    override val lastMessage: Message?,
    override val parent: CategoryChannel?,
) : GuildTextChannelLike {
    fun crosspostMessage(message: Message) = discordKt.scope.async {
        discordKt.routeExecutor.execute(ChannelRoutes.CROSSPOST_MESSAGE(id, message.id))
    }
    fun follow(webhookChannelId: Snowflake) = discordKt.scope.async {

    }
}
