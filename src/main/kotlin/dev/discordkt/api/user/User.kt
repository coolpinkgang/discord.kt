package dev.discordkt.api.user

import dev.discordkt.api.CacheableApiModel
import dev.discordkt.api.DiscordKt
import dev.discordkt.cache.Cache
import dev.discordkt.cache.CacheController
import dev.discordkt.snowflake.Snowflake
import dev.discordkt.models.user.User as ModelUser

class User(
    override val id: Snowflake,
    override val discordKt: DiscordKt
) : CacheableApiModel<ModelUser> {
    override val selfCache: Cache<ModelUser, User> = caches.lookup()
    val username: String by backing::username
    val bot: Boolean get() = backing.bot == true
    val discriminator: String by backing::discriminator
    val discriminatorInt: Int get() = discriminator.toInt()
    companion object {
        fun lookupCacheIn(cacheController: CacheController) =
            cacheController.lookup<ModelUser, User>()
    }
}
