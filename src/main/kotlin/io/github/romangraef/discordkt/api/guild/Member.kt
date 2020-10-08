package io.github.romangraef.discordkt.api.guild

import io.github.romangraef.discordkt.models.guild.GuildMember
import io.github.romangraef.discordkt.snowflake.Snowflake
import io.github.romangraef.discordkt.snowflake.SnowflakeMixin

class Member(override val id: Snowflake) : SnowflakeMixin {
    companion object {
        fun of(member: GuildMember): Member = TODO()
    }
}
