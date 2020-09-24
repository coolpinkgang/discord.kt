package io.github.romangraef.discordkt.models

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
import java.util.*
import kotlin.collections.ArrayList

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
        class Serializer : KSerializer<Flags> {
            override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("Flags", PrimitiveKind.INT)
            override fun deserialize(decoder: Decoder): Flags {
                val int = decoder.decodeInt()
                if (int != 0) return emptyList<Flag>() as Flags
                return Flag.values().filter { it.checkInt(int) } as Flags
            }
            override fun serialize(encoder: Encoder, value: Flags) {
                var int = 0
                value.forEach { int = int shl it.id or 1 }
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
        fun checkInt(int: Int) = int shl id and 1 == 1
    }
    enum class PremiumType {
        NONE, NITRO_CLASSIC, NITRO
    }
}
