package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.api.user.User
import io.github.romangraef.discordkt.snowflake.Snowflake

class DMGroupChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake
) : TextChannel, NameableChannel {
    val recipients: List<User> get() = backing.recipients!!.map { User.of(caches, it) }
    val ownerId: Snowflake get() = backing.ownerId!!
    suspend fun addRecipient(user: User, token: String, nick: String) = addRecipient(user.id, token, nick)
    suspend fun addRecipient(snowflake: Snowflake, token: String, nick: String): Unit = TODO()
    suspend fun removeRecipient(user: User) = removeRecipient(user.id)
    suspend fun removeRecipient(snowflake: Snowflake): Unit = TODO()
}
