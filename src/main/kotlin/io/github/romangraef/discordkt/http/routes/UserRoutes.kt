package io.github.romangraef.discordkt.http.routes

import io.github.romangraef.discordkt.models.channel.Channel
import io.github.romangraef.discordkt.models.guild.Guild
import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.user.*

object UserRoutes {
    fun GET_CURRENT_USER() =
        GET<User>("/users/@me")
    fun GET_USER(userId: Snowflake) =
        GET<User>("/users/$userId")
    fun MODIFY_CURRENT_USER() =
        PATCH<User, UserModifyCurrent>("/users/@me")
    fun GET_CURRENT_USER_GUILDS(before: Snowflake? = null, after: Snowflake? = null, limit: Int? = 100) =
        (GET<List<Guild>>("/users/@me/guilds")) {
            query("before", before)
            query("after", after)
            query("limit", limit)
        }
    fun LEAVE_GUILD(guildId: Snowflake) =
        DELETE<Unit>("/users/@me/guilds/$guildId")
    fun GET_USER_DMS() =
        GET<List<Unit>>("/users/@me/channels")
    fun CREATE_DM() =
        POST<Channel, DMCreate>("/users/@me/channels")
    fun CREATE_GROUP_DM() =
        POST<Channel, GroupDMCreate>("/users/@me/channels")
    fun GET_USER_CONNECTIONS() =
        GET<Connection>("/users/@me/connections")
}
