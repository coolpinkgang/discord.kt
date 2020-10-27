package dev.discordkt.http.routes

import dev.discordkt.models.invite.Invite

object InviteRoutes {
    fun GET_INVITE(inviteCode: String, withCounts: Boolean? = null) =
        (GET<Invite>("/invites/$inviteCode")) { query("withCounts", withCounts) }
    fun DELETE_INVITE(inviteCode: String) =
        DELETE<Invite>("/invites/$inviteCode")
}
