package dev.discordkt.models.user

import dev.discordkt.models.serial.ImageData

import kotlinx.serialization.Serializable

@Serializable
data class UserModifyCurrent(
    val username: String,
    val avatar: ImageData?
)
