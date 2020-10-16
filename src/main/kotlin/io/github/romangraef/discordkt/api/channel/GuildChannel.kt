package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.models.channel.Overwrite
import io.github.romangraef.discordkt.snowflake.Snowflake

interface GuildChannel : NameableChannel {
    val position: Int get() = backing.position!!
    val nsfw: Boolean get() = backing.nsfw!!
    val permissionOverwrites: List<Overwrite> get() = backing.permissionOverwrites!!
    val guildId: Snowflake get() = backing.guildId!!
    suspend fun changePosition(position: Int): Unit = TODO()
    suspend fun changePosition(before: GuildChannel, after: GuildChannel): Unit = TODO()
    suspend fun addPermissionOverwrite(overwrite: Overwrite): Unit = TODO()
    suspend fun removePermissionOverwrite(where: (Overwrite) -> Boolean): Unit = TODO()
    suspend fun updatePermissionOverwrite(update: (Overwrite) -> Overwrite): Unit = TODO()
    suspend fun toggleNsfw(): Unit = TODO()
    suspend fun createInvite(): String = TODO()
}
