package io.github.romangraef.discordkt.api.channel

import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.api.message.Message
import io.github.romangraef.discordkt.api.User
import io.github.romangraef.discordkt.http.routes.ChannelRoutes
import io.github.romangraef.discordkt.models.channel.GroupDMAddRecipient
import io.github.romangraef.discordkt.snowflake.Snowflake
import kotlinx.coroutines.async

class DMGroupChannel(
    override val discordKt: DiscordKt,
    override val id: Snowflake,
    override val name: String,
    override val lastMessage: Message?,
    val recipients: List<User>,
    val owner: User,
    val icon: String,
) : TextChannel, NameableChannel {

    fun addRecipient(newRecipient: User, accessToken: String, nick: String = newRecipient.name) = discordKt.scope.async {
        discordKt.routeExecutor.execute(ChannelRoutes.GROUP_DM_ADD_RECIPIENT(id, newRecipient.asSnowflake), GroupDMAddRecipient(accessToken, nick))
    }

    fun removeRecipient(recipient: User) = discordKt.scope.async {
        discordKt.routeExecutor.execute(ChannelRoutes.GROUP_DM_REMOVE_RECIPIENT(id, recipient.asSnowflake))
    }

}
