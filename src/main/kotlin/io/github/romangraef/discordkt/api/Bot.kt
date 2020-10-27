package io.github.romangraef.discordkt.api

import io.github.romangraef.discordkt.snowflake.Snowflake
import io.github.romangraef.discordkt.snowflake.SnowflakeMixin

class Bot(
    override val discordKt: DiscordKt
) : ApiModel, SnowflakeMixin {
    override lateinit var id: Snowflake
}
