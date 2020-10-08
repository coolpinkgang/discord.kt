package io.github.romangraef.discordkt.api.channel

import kotlinx.coroutines.async

interface NameableChannel : BaseChannel {
    val name: String
    fun rename(newName: String) = discordKt.scope.async {

    }
}
