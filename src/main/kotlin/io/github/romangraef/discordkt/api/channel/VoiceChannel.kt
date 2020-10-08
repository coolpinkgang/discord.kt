package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.api.guild.Guild
import io.github.romangraef.discordkt.api.permission.PermissionOverwrite
import io.github.romangraef.discordkt.snowflake.Snowflake
import kotlinx.coroutines.async

class VoiceChannel(
    override val discordKt: DiscordKt,
    override val parent: CategoryChannel?,
    override val guild: Guild,
    override val position: Int,
    override val permissionOverwrites: List<PermissionOverwrite>,
    override val nsfw: Boolean,
    override val name: String,
    override val id: Snowflake
) : GuildNonCategoryChannel {

    fun changeBitrate(absolutBitrate: Int) = discordKt.scope.async {

    }

    fun changeUserLimit(absolutUserLimit: Int) = discordKt.scope.async {

    }

}
