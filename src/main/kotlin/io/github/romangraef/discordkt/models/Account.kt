package io.github.romangraef.discordkt.models

import kotlinx.serialization.Serializable

@Serializable
data class Account(
        val id: String,
        val name: String
)
