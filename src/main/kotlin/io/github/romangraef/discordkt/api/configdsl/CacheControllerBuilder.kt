package io.github.romangraef.discordkt.api.configdsl

import io.github.romangraef.discordkt.api.User
import io.github.romangraef.discordkt.api.guild.Guild
import io.github.romangraef.discordkt.api.message.Message
import io.github.romangraef.discordkt.cash.CacheController

@DiscordKtDsl
class CacheControllerBuilder {
    private val userCachePolicyBuilder = CachePolicyBuilder<User>()
    private val guildCachePolicyBuilder = CachePolicyBuilder<Guild>()
    private val messageCachePolicyBuilder = CachePolicyBuilder<Message>()
    fun users(block: CachePolicyBuilder<User>.() -> Unit): Unit = userCachePolicyBuilder.run(block)
    fun guilds(block: CachePolicyBuilder<Guild>.() -> Unit): Unit = guildCachePolicyBuilder.run(block)
    fun messages(block: CachePolicyBuilder<Message>.() -> Unit): Unit = messageCachePolicyBuilder.run(block)
    fun build() = CacheController(
        userCachePolicyBuilder.build(),
        guildCachePolicyBuilder.build(),
        messageCachePolicyBuilder.build()
    )
}
