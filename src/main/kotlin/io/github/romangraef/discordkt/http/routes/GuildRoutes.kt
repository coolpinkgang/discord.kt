package io.github.romangraef.discordkt.http.routes

import io.github.romangraef.discordkt.models.channel.Channel
import io.github.romangraef.discordkt.models.guild.Ban
import io.github.romangraef.discordkt.models.guild.Guild
import io.github.romangraef.discordkt.models.guild.GuildAddMember
import io.github.romangraef.discordkt.models.guild.GuildBeginPrune
import io.github.romangraef.discordkt.models.guild.GuildCreate
import io.github.romangraef.discordkt.models.guild.GuildCreateBan
import io.github.romangraef.discordkt.models.guild.GuildCreateChannel
import io.github.romangraef.discordkt.models.guild.GuildCreateIntegration
import io.github.romangraef.discordkt.models.guild.GuildCreateRole
import io.github.romangraef.discordkt.models.guild.GuildMember
import io.github.romangraef.discordkt.models.guild.GuildModify
import io.github.romangraef.discordkt.models.guild.GuildModifyChannelPositions
import io.github.romangraef.discordkt.models.guild.GuildModifyIntegration
import io.github.romangraef.discordkt.models.guild.GuildModifyRole
import io.github.romangraef.discordkt.models.guild.GuildModifyRolePosition
import io.github.romangraef.discordkt.models.guild.GuildModifyUser
import io.github.romangraef.discordkt.models.guild.GuildPreview
import io.github.romangraef.discordkt.models.guild.GuildWidget
import io.github.romangraef.discordkt.models.guild.Integration
import io.github.romangraef.discordkt.models.guild.ModifyCurrentUserNick
import io.github.romangraef.discordkt.models.guild.WidgetStyleOption
import io.github.romangraef.discordkt.models.invite.Invite
import io.github.romangraef.discordkt.models.permissions.Role
import io.github.romangraef.discordkt.models.serial.Snowflake
import io.github.romangraef.discordkt.models.voice.VoiceRegion
import io.ktor.http.HttpMethod
import kotlinx.serialization.json.JsonElement

object GuildRoutes {
    fun CREATE_GUILD() =
        POST<Guild, GuildCreate>("/guilds")
    fun GET_GUILD(guildId: Snowflake, withCounts: Boolean = false) =
        (GET<Guild>("/guilds/$guildId")) { query("with_counts", withCounts) }
    fun GET_GUILD_PREVIEW(guildId: Snowflake) =
        GET<GuildPreview>("/guilds/$guildId/preview")
    fun MODIFY_GUILD(guildId: Snowflake) =
        PATCH<Guild, GuildModify>("/guilds/$guildId")
    fun DELETE_GUILD(guildId: Snowflake) =
        DELETE<Unit>("/guilds/$guildId")
    fun GET_GUILD_CHANNELS(guildId: Snowflake) =
        GET<List<Channel>>("/guilds/$guildId/channels")
    fun CREATE_GUILD_CHANNEL(guildId: Snowflake) =
        POST<Channel, GuildCreateChannel>("/guilds/$guildId/channels")
    fun MODIFY_GUILD_CHANNEL_POSITIONS(guildId: Snowflake) =
        PATCH<Unit, GuildModifyChannelPositions>("/guilds/$guildId/channels")
    fun GET_GUILD_MEMBER(guildId: Snowflake, userId: Snowflake) =
        GET<GuildMember>("/guilds/$guildId/members/$userId")
    fun LIST_GUILD_MEMBERS(guildId: Snowflake) =
        GET<List<GuildMember>>("/guilds/$guildId/members")
    fun ADD_GUILD_MEMBER(guildId: Snowflake, userId: Snowflake) =
        PUT<Unit, GuildAddMember>("/guilds/$guildId/members/$userId")
    fun MODIFY_GUILD_MEMBER(guildId: Snowflake, userId: Snowflake) =
        PATCH<Unit, GuildModifyUser>("/guilds/$guildId/members/$userId")
    fun MODIFY_CURRENT_USER_NICK(guildId: Snowflake) =
        PATCH<String, ModifyCurrentUserNick>("/guilds/$guildId/members/@me/nick")
    fun ADD_GUILD_MEMBER_ROLE(guildId: Snowflake, userId: Snowflake, roleId: Snowflake) =
        PUT<Unit, Unit>("/guilds/$guildId/members/$userId/roles/$roleId")
    fun REMOVE_GUILD_MEMBER_ROLE(guildId: Snowflake, userId: Snowflake, roleId: Snowflake) =
        DELETE<Unit>("/guilds/$guildId/members/$userId/roles/$roleId")
    fun REMOVE_GUILD_MEMBER(guildId: Snowflake, userId: Snowflake) =
        DELETE<Unit>("/guilds/$guildId/members/$userId")
    fun GET_GUILD_BANS(guildId: Snowflake) =
        GET<List<Ban>>("/guilds/$guildId/bans")
    fun GET_GUILD_BAN(guildId: Snowflake, userId: Snowflake) =
        GET<Ban>("/guilds/$guildId/bans/$userId")
    fun CREATE_GUILD_BAN(guildId: Snowflake, userId: Snowflake) =
        PUT<Unit, GuildCreateBan>("/guilds/$guildId/bans/$userId")
    fun REMOVE_GUILD_BAN(guildId: Snowflake, userId: Snowflake) =
        DELETE<Unit>("/guilds/$guildId/bans/$userId")
    fun GET_GUILD_ROLES(guildId: Snowflake) =
        GET<List<Role>>("/guilds/$guildId/roles")
    fun CREATE_GUILD_ROLE(guildId: Snowflake) =
        POST<Role, GuildCreateRole>("/guilds/$guildId/roles")
    fun MODIFY_GUILD_ROLE_POSITION(guildId: Snowflake) =
        PATCH<List<Role>, GuildModifyRolePosition>("/guilds/$guildId/roles")
    fun MODIFY_GUILD_ROLE(guildId: Snowflake, roleId: Snowflake) =
        PATCH<Role, GuildModifyRole>("/guilds/$guildId/roles/$roleId")
    fun DELETE_GUILD_ROLE(guildId: Snowflake, roleId: Snowflake) =
        DELETE<Unit>("/guilds/$guildId/roles/$roleId")
    fun GET_GUILD_PRUNE_COUNT(guildId: Snowflake, days: Int = 7, includeRoles: List<Snowflake>) =
        (GET<Int>("/guilds/$guildId/prune")) {
            query("days", days)
            query("include_roles", includeRoles.joinToString(","))
        }
    fun BEGIN_GUILD_PRUNE(guildId: Snowflake) =
        POST<GuildBeginPrune.In, GuildBeginPrune.Out>("/guilds/$guildId/prune")
    fun GET_GUILD_VOICE_REGIONS(guildId: Snowflake) =
        GET<List<VoiceRegion>>("/guilds/$guildId/regions")
    fun GET_GUILD_INVITES(guildId: Snowflake) =
        GET<List<Invite>>("/guilds/$guildId/invites")
    fun GET_GUILD_INTEGRATIONS(guildId: Snowflake) =
        GET<List<Integration>>("/guilds/$guildId/integrations")
    fun CREATE_GUILD_INTEGRATION(guildId: Snowflake) =
        POST<Unit, GuildCreateIntegration>("/guilds/$guildId/integrations")
    fun MODIFY_GUILD_INTEGRATION(guildId: Snowflake, integrationId: Snowflake) =
        PATCH<Unit, GuildModifyIntegration>("/guilds/$guildId/integration/$integrationId")
    fun DELETE_GUILD_INTEGRATION(guildId: Snowflake, integrationId: Snowflake) =
        DELETE<Unit>("/guilds/$guildId/integrations/$integrationId")

    fun SYNC_GUILD_INTEGRATION(guildId: Snowflake, integrationId: Snowflake) =
        POST<Unit, Unit>("/guilds/$guildId/integrations/$integrationId/sync")

    fun GET_GUILD_WIDGET_SETTINGS(guildId: Snowflake) =
        GET<GuildWidget>("/guilds/$guildId/widget")

    fun MODIFY_GUILD_WIDGET(guildId: Snowflake) =
        PATCH<GuildWidget, GuildWidget>("/guilds/$guildId/widget")

    fun GET_GUILD_WIDGET(guildId: Snowflake) =
        GET<JsonElement>("/guilds/$guildId/widget.json")

    fun GET_GUILD_VANITY_URL(guildId: Snowflake) =
        GET<Invite>("/guilds/$guildId/vanity-url")

    fun GET_GUILD_WIDGET_IMAGE(guildId: Snowflake, widgetStyle: WidgetStyleOption? = null) =
        (Route(HttpMethod.Get, "/guilds/$guildId/widget.png", ByteResultResponseMaker, NoBodyRequestMaker)) {
            query("style", widgetStyle)
        }
}
