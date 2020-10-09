package io.github.romangraef.discordkt.cash

import io.github.romangraef.discordkt.api.User
import io.github.romangraef.discordkt.api.guild.Guild
import io.github.romangraef.discordkt.api.message.Message

class CacheController(
    userCachePolicy: CachePolicy<User>,
    guildCachePolicy: CachePolicy<Guild>,
    messageCachePolicy: CachePolicy<Message>,
) {
    val userCache = Cache<User>(userCachePolicy)
    val guildCache = Cache<Guild>(guildCachePolicy)
    val messageCache = Cache<Message>(messageCachePolicy)
}
