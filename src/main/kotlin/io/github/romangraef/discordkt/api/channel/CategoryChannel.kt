package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.api.guild.Guild
import io.github.romangraef.discordkt.api.permission.PermissionOverwrite
import io.github.romangraef.discordkt.snowflake.Snowflake

class CategoryChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake,
    override val guild: Guild,
    override val name: String,
    override val position: Int,
    override val permissionOverwrites: List<PermissionOverwrite>,
    override val nsfw: Boolean
) : GuildChannel {
}
