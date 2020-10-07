package io.github.romangraef.discordkt.models.guild

enum class WidgetStyleOption {
    SHIELD,
    BANNER1,
    BANNER2,
    BANNER3,
    BANNER4;

    override fun toString(): String = name.toLowerCase()
}
