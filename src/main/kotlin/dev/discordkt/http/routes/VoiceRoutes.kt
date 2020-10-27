package dev.discordkt.http.routes

import dev.discordkt.models.voice.VoiceRegion

object VoiceRoutes {
    fun LIST_VOICE_REGIONS() =
        GET<List<VoiceRegion>>("/voice/regions")
}
