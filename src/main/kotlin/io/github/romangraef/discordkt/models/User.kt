package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.serial.FlagsSerializer
import io.github.romangraef.discordkt.models.serial.OrdinalSerializer
import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.serial.SnowflakeMixin

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

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
    val flags: Flags = emptyList<Flag>() as Flags,
    @SerialName("permium_type")
    val premiumType: PremiumType = PremiumType.NONE,
    @SerialName("public_flags")
    val publicFlags: Flags = emptyList<Flag>() as Flags
) : SnowflakeMixin() {
    @Serializable(with = Flags.Serializer::class)
    interface Flags : List<Flag> {
        class Serializer : FlagsSerializer<Flag>(Flag::values)
    }
    enum class Flag {
        DISCORD_EMPLOYEE,
        DISCORD_PARTNER,
        HYPESQUAD_EVENTS,
        BUG_HUNTER_LEVEL_1,
        NULL_4,
        NULL_5,
        HOUSE_BRAVERY,
        HOUSE_BRILLIANCE,
        HOUSE_BALANCE,
        EARLY_SUPPORTER,
        TEAM_USER,
        SYSTEM,
        NULL_13,
        BUG_HUNTER_LEVEL_2,
        NULL_15,
        VERIFIED_BOT,
        VERIFIED_BOT_DEVELOPER;
    }

    @Serializable(with = PremiumType.Serializer::class)
    enum class PremiumType(val id: Int) {
        NONE(0), NITRO_CLASSIC(1), NITRO(2);

        class Serializer : OrdinalSerializer<PremiumType>(values())
    }
}
