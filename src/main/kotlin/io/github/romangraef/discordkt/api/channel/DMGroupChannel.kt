package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.api.exception.InsufficientPermissionsException
import io.github.romangraef.discordkt.api.user.User
import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.http.routes.ChannelRoutes
import io.github.romangraef.discordkt.models.channel.Channel
import io.github.romangraef.discordkt.models.channel.GroupDMAddRecipient
import io.github.romangraef.discordkt.models.user.User as UserModel
import io.github.romangraef.discordkt.snowflake.Snowflake

class DMGroupChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake
) : TextChannel, NameableChannel {
    override val selfCache: Cache<Channel, DMGroupChannel> = caches.lookup()
    val recipients: List<User> get() = backing.recipients!!.map { User.lookupCacheIn(caches)[it.id]!! }
    val owner: User get() = User.lookupCacheIn(caches)[backing.ownerId!!]!!
    suspend fun addRecipient(user: User, token: String, nick: String) = addRecipient(user.id, token, nick)
    suspend fun addRecipient(snowflake: Snowflake, token: String, nick: String): Unit =
        routeExecutor.execute(ChannelRoutes.GROUP_DM_ADD_RECIPIENT(id, snowflake), GroupDMAddRecipient(token, nick))
    suspend fun removeRecipient(user: User) = removeRecipient(user.id)
    suspend fun removeRecipient(snowflake: Snowflake): Unit = if (discordKt.bot.id == owner.id)
        routeExecutor.execute(ChannelRoutes.GROUP_DM_REMOVE_RECIPIENT(id, snowflake))
    else throw InsufficientPermissionsException()
}
