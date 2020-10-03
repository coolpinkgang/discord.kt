package io.github.romangraef.discordkt.models.guild

import io.github.romangraef.discordkt.models.serial.EnumNameSerializer
import kotlinx.serialization.Serializable

@Serializable(with = GuildFeature.Serializer::class)
enum class GuildFeature {
    INVITE_SPLASH,
    VIP_REGIONS,
    VANITY_URL,
    VERIFIED,
    PARTNERED,
    PUBLIC,
    COMMERCE,
    NEWS,
    DISCOVERABLE,
    FEATURABLE,
    ANIMATED_ICON,
    BANNER,
    PUBLIC_DISABLED,
    WELCOME_SCREEN_ENABLED;
    class Serializer : EnumNameSerializer<GuildFeature>(GuildFeature::valueOf)
}
