package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.api.message.Message
import io.github.romangraef.discordkt.api.User
import io.github.romangraef.discordkt.snowflake.Snowflake

class DMChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake,
    override val lastMessage: Message?,
    val recipient: User
) : TextChannel {
}
