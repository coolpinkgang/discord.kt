package dev.discordkt.api.message

import dev.discordkt.api.CacheableApiModel
import dev.discordkt.api.DiscordKt
import dev.discordkt.cache.Cache
import dev.discordkt.cache.CacheController
import dev.discordkt.snowflake.Snowflake
import dev.discordkt.models.channel.Message as ModelMessage

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
