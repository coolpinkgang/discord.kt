@file:UseSerializers(ISO8601Serializer::class)
package io.github.romangraef.discordkt.models.invite

import io.github.romangraef.discordkt.models.serial.ISO8601Serializer
import kotlinx.serialization.SerialName

import java.time.OffsetDateTime

import kotlinx.serialization.Serializable
import kotlinx.serialization.UseSerializers

@Serializable
data class InviteMetadata(
    val uses: Int,
    @SerialName("max_uses")
    val maxUses: Int,
    @SerialName("max_age")
    val maxAge: Int,
    val temporary: Boolean,
    @SerialName("created_at")
    val createdAt: OffsetDateTime
)
