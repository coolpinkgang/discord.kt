package io.github.romangraef.discordkt.api.configdsl

import io.github.romangraef.discordkt.api.user.User
import io.github.romangraef.discordkt.api.channel.BaseChannel
import io.github.romangraef.discordkt.api.guild.Guild
import io.github.romangraef.discordkt.api.message.Message
import io.github.romangraef.discordkt.cache.CacheController

@DiscordKtDsl
class CacheControllerBuilder {
    private val userCachePolicyBuilder = CachePolicyBuilder<User>()
    private val guildCachePolicyBuilder = CachePolicyBuilder<Guild>()
    private val channelCachePolicyBuilder = CachePolicyBuilder<BaseChannel>()
    private val messageCachePolicyBuilder = CachePolicyBuilder<Message>()
    fun users(block: CachePolicyBuilder<User>.() -> Unit): Unit = userCachePolicyBuilder.run(block)
    fun guilds(block: CachePolicyBuilder<Guild>.() -> Unit): Unit = guildCachePolicyBuilder.run(block)
    fun channels(block: CachePolicyBuilder<BaseChannel>.() -> Unit): Unit = channelCachePolicyBuilder.run(block)
    fun messages(block: CachePolicyBuilder<Message>.() -> Unit): Unit = messageCachePolicyBuilder.run(block)
    fun build() = CacheController(
        userCachePolicyBuilder.build(),
        guildCachePolicyBuilder.build(),
        channelCachePolicyBuilder.build(),
        messageCachePolicyBuilder.build()
    )
}
