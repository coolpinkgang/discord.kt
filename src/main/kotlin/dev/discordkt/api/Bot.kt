package dev.discordkt.api

import dev.discordkt.snowflake.Snowflake
import dev.discordkt.snowflake.SnowflakeMixin

class Bot(
    override val discordKt: DiscordKt
) : ApiModel, SnowflakeMixin {
    override lateinit var id: Snowflake
}
