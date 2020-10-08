package io.github.romangraef.discordkt.api.permission

import io.github.romangraef.discordkt.snowflake.Snowflake
import io.github.romangraef.discordkt.snowflake.SnowflakeMixin

class Role(override val id: Snowflake) : SnowflakeMixin
