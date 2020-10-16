package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.snowflake.Snowflake

class GuildVoiceChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake
) : GuildChildChannel {
    val bitrate: Int get() = backing.bitrate!!
    val userLimit: Int get() = backing.userLimit!!
    suspend fun changeBitrate(bitrate: Int): Unit = TODO()
    suspend fun changeUserLimit(userLimit: Int): Unit = TODO()
}
