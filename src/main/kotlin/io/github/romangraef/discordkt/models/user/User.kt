package io.github.romangraef.discordkt.models.user

import io.github.romangraef.discordkt.snowflake.Snowflake
import io.github.romangraef.discordkt.snowflake.SnowflakeMixin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    override val id: Snowflake,
    val username: String,
    val discriminator: String,
    @SerialName("avatar")
    val avatarHash: String?,
    val bot: Boolean? = null,
    val system: Boolean? = null,
    @SerialName("mfa_enabled")
    val mfaEnabled: Boolean? = null,
    val locale: String? = null,
    val verified: Boolean? = null,
    val email: String? = null,
    @SerialName("flags")
    val flags: UserFlag.BitField? = null,
    @SerialName("premium_type")
    val premiumType: PremiumType? = null,
    @SerialName("public_flags")
    val publicFlags: UserFlag.BitField? = null
) : SnowflakeMixin
