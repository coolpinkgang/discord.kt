package io.github.romangraef.discordkt.http.routes

import io.github.romangraef.discordkt.models.voice.VoiceRegion

object VoiceRoutes {
    fun LIST_VOICE_REGIONS() =
        GET<List<VoiceRegion>>("/voice/regions")
}
