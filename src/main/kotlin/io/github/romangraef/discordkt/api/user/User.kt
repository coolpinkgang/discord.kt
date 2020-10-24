package io.github.romangraef.discordkt.api.user

import io.github.romangraef.discordkt.api.CacheableApiModel
import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.cache.CacheController
import io.github.romangraef.discordkt.snowflake.Snowflake
import io.github.romangraef.discordkt.models.user.User as ModelUser

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
