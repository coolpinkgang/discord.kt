package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.guild.Guild
import io.github.romangraef.discordkt.api.permission.PermissionOverwrite
import kotlinx.coroutines.async

interface GuildChannel : NameableChannel {
    val guild: Guild
    val position: Int
    val permissionOverwrites: List<PermissionOverwrite>
    val nsfw: Boolean
    fun changePosition(absolutPosition: Int) = discordKt.scope.async {

    }
    fun changePosition(before: GuildChannel? = null, after: GuildChannel? = null) = discordKt.scope.async {

    }
    fun addPermissionOverwrite(permissionOverwrite: PermissionOverwrite) = discordKt.scope.async {

    }
    fun removePermissionOverwrite(where: (PermissionOverwrite) -> Boolean) = discordKt.scope.async {

    }
    fun updatePermissionOverwrite(update: (PermissionOverwrite) -> PermissionOverwrite?) = discordKt.scope.async {

    }
    fun toggleNsfw() = discordKt.scope.async {

    }
    fun getInvites() = discordKt.scope.async {

    }
    fun createInvite() = discordKt.scope.async {

    }
}
