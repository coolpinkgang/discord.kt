@file:UseSerializers(ISO8601Serializer::class)
package dev.discordkt.models.channel

import dev.discordkt.models.Color
import dev.discordkt.models.serial.ISO8601Serializer

import java.time.OffsetDateTime

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

@Serializable
data class Embed(
    val title: String? = null,
    val type: String? = null,
    val description: String? = null,
    val url: String? = null,
    val timestamp: OffsetDateTime? = null,
    val color: Color? = null,
    val footer: EmbedFooter? = null,
    val image: EmbedImage? = null,
    val video: EmbedVideo? = null,
    val provider: EmbedProvider? = null,
    val author: EmbedAuthor? = null,
    val fields: List<EmbedField>? = null
)
