package dev.discordkt.snowflake

interface SnowflakeMixin : BaseSnowflake {
    val id: Snowflake
    override val longId: Long get() = id.longId
    override val stringId: String get() = id.stringId
    override val asSnowflake: Snowflake get() = id
}
