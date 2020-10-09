package io.github.romangraef.discordkt.cache

import io.github.romangraef.discordkt.api.user.User
import io.github.romangraef.discordkt.api.channel.BaseChannel
import io.github.romangraef.discordkt.api.guild.Guild
import io.github.romangraef.discordkt.api.message.Message

class CacheController(
    userCachePolicy: CachePolicy<User>,
    guildCachePolicy: CachePolicy<Guild>,
    channelCachePolicy: CachePolicy<BaseChannel>,
    messageCachePolicy: CachePolicy<Message>,
) {
    val userCache = Cache(userCachePolicy)
    val guildCache = Cache(guildCachePolicy)
    val channelCache = Cache(channelCachePolicy)
    val messageCache = Cache(messageCachePolicy)
}
