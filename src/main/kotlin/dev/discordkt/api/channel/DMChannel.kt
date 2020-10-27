package dev.discordkt.api.channel

import dev.discordkt.api.DiscordKt
import dev.discordkt.api.user.User
import dev.discordkt.cache.Cache
import dev.discordkt.models.channel.Channel
import dev.discordkt.snowflake.Snowflake

class DMChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake
) : TextChannel {
    override val selfCache: Cache<Channel, DMChannel> = caches.lookup()
    val recipient: User get() = User.lookupCacheIn(caches)[backing.recipients!!.first().id]!!
}
