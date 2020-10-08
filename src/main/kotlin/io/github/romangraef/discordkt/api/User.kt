package io.github.romangraef.discordkt.api

import io.github.romangraef.discordkt.models.user.User
import io.github.romangraef.discordkt.snowflake.Snowflake
import io.github.romangraef.discordkt.snowflake.SnowflakeMixin

class User(
    override val id: Snowflake,
    val name: String
) : SnowflakeMixin {
    companion object {
        fun of(user: User): io.github.romangraef.discordkt.api.User = TODO()
    }
}
