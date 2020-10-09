package io.github.romangraef.discordkt.api.configdsl

import io.github.romangraef.discordkt.api.message.Message

val CachePolicyBuilder<Message>.isGuildMessage: (Message) -> Boolean get() = { it.guild != null }
val CachePolicyBuilder<Message>.isPinned: (Message) -> Boolean get() = { it.pinned }
val CachePolicyBuilder<Message>.isFromBot: (Message) -> Boolean get() = { TODO() }
val CachePolicyBuilder<Message>.isFromHuman: (Message) -> Boolean get() = { TODO() }
