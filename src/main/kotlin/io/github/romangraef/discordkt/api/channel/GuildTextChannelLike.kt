package io.github.romangraef.discordkt.api.channel

import kotlinx.coroutines.async

interface GuildTextChannelLike : GuildNonCategoryChannel, TextChannel {
    val topic: String
    fun changeType() = discordKt.scope.async {

    }

    fun changeTopic(newTopic: String) = discordKt.scope.async {

    }
}
