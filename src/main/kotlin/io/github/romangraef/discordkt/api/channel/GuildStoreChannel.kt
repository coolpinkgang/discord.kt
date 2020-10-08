package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.api.guild.Guild
import io.github.romangraef.discordkt.api.permission.PermissionOverwrite
import io.github.romangraef.discordkt.snowflake.Snowflake

class GuildStoreChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake,
    override var name: String,
    override val guild: Guild,
    override val position: Int,
    override val permissionOverwrites: List<PermissionOverwrite>,
    override val nsfw: Boolean,
    override val parent: CategoryChannel?,
) : GuildNonCategoryChannel {
}
