package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.api.user.User
import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.models.channel.Channel
import io.github.romangraef.discordkt.models.user.User as UserModel
import io.github.romangraef.discordkt.snowflake.Snowflake

class DMChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake
) : TextChannel {
    override val selfCache: Cache<Channel, DMChannel> = caches.lookup()
    val recipient: User get() = User.lookupCacheIn(caches)[backing.recipients!!.first().id]!!
}
