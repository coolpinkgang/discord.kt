package io.github.romangraef.discordkt.api.channel

import kotlinx.coroutines.async

interface GuildNonCategoryChannel : GuildChannel {
    val parent: CategoryChannel?
    fun changeParent(newParent: CategoryChannel?) = discordKt.scope.async {

    }
}
