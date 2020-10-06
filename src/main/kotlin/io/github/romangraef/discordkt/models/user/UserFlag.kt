package io.github.romangraef.discordkt.models.user

import io.github.romangraef.discordkt.models.serial.FlagsSerializer
import kotlinx.serialization.Serializable

enum class UserFlag {
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
    NULL_12,
    SYSTEM,
    NULL_13,
    BUG_HUNTER_LEVEL_2,
    NULL_15,
    VERIFIED_BOT,
    VERIFIED_BOT_DEVELOPER;
    @Serializable(with = BitField.Serializer::class)
    class BitField(val backingList: List<UserFlag>) : List<UserFlag> by backingList {
        class Serializer : FlagsSerializer<UserFlag, BitField>(UserFlag::values, ::BitField)
    }
}
