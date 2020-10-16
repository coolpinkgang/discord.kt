package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.snowflake.Snowflake

class GuildCategoryChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake
) : GuildChannel {

}
