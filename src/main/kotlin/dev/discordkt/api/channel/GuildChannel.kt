package dev.discordkt.api.channel

import dev.discordkt.cache.Cache
import dev.discordkt.models.channel.Channel
import dev.discordkt.models.channel.Overwrite
import dev.discordkt.snowflake.Snowflake

interface GuildChannel : NameableChannel {
    override val selfCache: Cache<Channel, out GuildChannel>
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
