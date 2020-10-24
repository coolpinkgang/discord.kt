package io.github.romangraef.discordkt.api.message

import io.github.romangraef.discordkt.api.CacheableApiModel
import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.cache.CacheController
import io.github.romangraef.discordkt.snowflake.Snowflake
import io.github.romangraef.discordkt.models.channel.Message as ModelMessage

class Message(
    override val discordKt: DiscordKt,
    override val id: Snowflake,
) : CacheableApiModel<ModelMessage> {
    override val selfCache: Cache<ModelMessage, Message> =
        caches.lookup()
    companion object {
        fun lookupCacheIn(cacheController: CacheController) =
            cacheController.lookup<ModelMessage, Message>()
    }
}
