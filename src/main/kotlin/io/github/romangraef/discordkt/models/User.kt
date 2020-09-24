package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.serial.OrdinalSerializer
import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.serial.SnowflakeMixin
import kotlinx.serialization.KSerializer
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
    val flags: Flags = Flags(emptyList()),
    @SerialName("premium_type")
    val premiumType: PremiumType = PremiumType.NONE,
    @SerialName("public_flags")
    val publicFlags: Flags = Flags(emptyList())
) : SnowflakeMixin() {
    @Serializable(with = Flags.Serializer::class)
    class Flags(backingList: List<Flag>) : List<Flag> by backingList {
        class Serializer : KSerializer<Flags> {
            override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Flags", PrimitiveKind.INT)
            override fun deserialize(decoder: Decoder): Flags {
                val int = decoder.decodeInt()
                if (int == 0) return Flags(emptyList())
                return Flags(Flag.values().filter { it.checkInt(int) })
            }

            override fun serialize(encoder: Encoder, value: Flags) {
                var int = 0
                value.forEach { int = 1 shl it.id or int }
                encoder.encodeInt(int)
            }
        }
    }
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
        VERIFIED_BOT_DEVELOPER(17);

        fun checkInt(int: Int) = int ushr  id and 1 == 1
    }

    @Serializable(with = PremiumType.Serializer::class)
    enum class PremiumType(val id: Int) {
        NONE(0), NITRO_CLASSIC(1), NITRO(2);

        class Serializer : OrdinalSerializer<PremiumType>(values())
    }
}
