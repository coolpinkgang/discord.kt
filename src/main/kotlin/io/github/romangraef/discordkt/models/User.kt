package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.serial.SnowflakeMixin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    override val id: Snowflake,
    val username: String,
    val discriminator: String,
    @SerialName("avatar") val avatarHash: String?,
    val bot: Boolean? = null,
    val system: Boolean? = null,
    @SerialName("mfa_enabled") val mfaEnabled: Boolean? = null,
    val locale: String? = null,
    val verified: Boolean? = null,
    val email: String? = null,
    @SerialName("flags") val intFlags: Int = 0,
    @SerialName("permium_type") val intPremiumType: Int = 0,
    @SerialName("public_flags") val intPublicFlags: Int = 0
) : SnowflakeMixin() {
    enum class Flag(val id: Int) {
        DISCORD_EMPLOYEE(0),
        DISCORD_PARTNER(1),
        HYPESQUAD_EVENTS(2),
        BUG_HUNTER_LEVEL_1(3),
        HOUSE_BRAVERY(6),
        HOUSE_BRILLIANCE(7),
        HOUSE_BALANCE(8),
        EARLY_SUPPORTER(9),
        TEAM_USER(10),
        SYSTEM(12),
        BUG_HUNTER_LEVEL_2(14),
        VERIFIED_BOT(16),
        VERIFIED_BOT_DEVELOPER(17)
    }
    enum class PremiumType {
        NONE, NITRO_CLASSIC, NITRO
    }
}
