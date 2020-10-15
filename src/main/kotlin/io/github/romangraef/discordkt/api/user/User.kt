package io.github.romangraef.discordkt.api.user

import io.github.romangraef.discordkt.api.ApiModel
import io.github.romangraef.discordkt.api.CachedApiModel
import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.cache.Cache
import io.github.romangraef.discordkt.models.user.User as ModelUser
import io.github.romangraef.discordkt.snowflake.Snowflake

class User(
    val id: Snowflake,
    override val discordKt: DiscordKt
) : CachedApiModel<ModelUser> {
    override val selfCache: Cache<ModelUser, User> = caches.lookup<User>() as Cache<ModelUser,User>
}
