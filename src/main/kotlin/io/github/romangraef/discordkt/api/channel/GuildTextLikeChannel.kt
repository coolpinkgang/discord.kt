package io.github.romangraef.discordkt.api.channel

interface GuildTextLikeChannel : GuildChannel, TextChannel {
    val topic get() = backing.topic
    suspend fun changeTopic(topic: String): Unit = TODO()
    suspend fun changeType(): GuildTextLikeChannel
}
