package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.ApiModel
import io.github.romangraef.discordkt.models.channel.Channel
import io.github.romangraef.discordkt.snowflake.SnowflakeMixin
import kotlinx.coroutines.async

interface BaseChannel : SnowflakeMixin, ApiModel {
    fun delete() = discordKt.scope.async {

    }
    companion object {
        fun of(channel: Channel): BaseChannel = TODO()
    }
}
