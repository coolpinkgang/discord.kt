package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.api.user.User
import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.models.channel.Channel
import io.github.romangraef.discordkt.models.user.User as UserModel
import io.github.romangraef.discordkt.snowflake.Snowflake

class DMGroupChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake
) : TextChannel, NameableChannel {
    override val selfCache: Cache<Channel, DMGroupChannel> = caches.lookup()
    val recipients: List<User> get() = backing.recipients!!.map { caches.lookup<UserModel, User>()[it.id]!! }
    val ownerId: Snowflake get() = backing.ownerId!!
    suspend fun addRecipient(user: User, token: String, nick: String) = addRecipient(user.id, token, nick)
    suspend fun addRecipient(snowflake: Snowflake, token: String, nick: String): Unit = TODO()
    suspend fun removeRecipient(user: User) = removeRecipient(user.id)
    suspend fun removeRecipient(snowflake: Snowflake): Unit = TODO()
}
