package io.github.romangraef.discordkt.models.user

import io.github.romangraef.discordkt.models.serial.ImageData
import kotlinx.serialization.Serializable

@Serializable
data class UserModifyCurrent(
    val username: String,
    val avatar: ImageData?
)
