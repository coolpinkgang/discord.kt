package io.github.romangraef.discordkt.api.user

import io.github.romangraef.discordkt.snowflake.Snowflake
import io.github.romangraef.discordkt.snowflake.SnowflakeMixin

class User(
    override val id: Snowflake,
    val name: String,
    val discriminator: String,
    val avatar: String?,
    val bot: Boolean,
    val system: Boolean,
    val mfaEnabled: Boolean,
    val locale: String,
    val verified: Boolean,
    val email: String?,
    val flags: List<UserFlag>,
    val premiumType: PremiumType,
) : SnowflakeMixin {
    companion object {
        fun of(user: io.github.romangraef.discordkt.models.user.User): User = TODO()
    }
}
