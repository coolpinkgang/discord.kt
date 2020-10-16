package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.api.user.User
import io.github.romangraef.discordkt.snowflake.Snowflake

class DMChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake
) : TextChannel {
    val recipient: User get() = User.of(caches, backing.recipients!!.first())
}
