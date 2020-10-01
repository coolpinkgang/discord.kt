package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.emoji.Emoji
import io.github.romangraef.discordkt.models.serial.FlagsSerializer
import io.github.romangraef.discordkt.models.serial.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Activity(
    val name: String,
    val type: Type,
    val url: String? = null,
    @SerialName("created_at")
    val createdAt: Long,
    val timestamps: Timestamps = Timestamps(),
    @SerialName("application_id")
    val applicationId: Snowflake? = null,
    val details: String? = null,
    val state: String? = null,
    val emoji: Emoji? = null,
    val party: Party = Party(),
    val assets: Assets = Assets(),
    val secrets: Secrets = Secrets(),
    val instance: Boolean? = null,
    val flags: Flags = Flags(emptyList())
) {
    enum class Type {
        GAME,
        STREAMING,
        LISTENING,
        CUSTOM
    }
    @Serializable
    data class Timestamps(
        val start: Int? = null,
        val end: Int? = null
    )
    @Serializable
    data class Party (
        val id: String? = null,
        val size: List<Int>? = null
    )
    @Serializable
    data class Assets(
        @SerialName("large_image")
        val largeImage: String? = null,
        @SerialName("large_text")
        val largeText: String? = null,
        @SerialName("small_image")
        val smallImage: String? = null,
        @SerialName("small_text")
        val smallText: String? = null
    )
    @Serializable
    data class Secrets(
        val join: String? = null,
        val spectate: String? = null,
        val match: String? = null
    )
    @Serializable(with = Flags.Serializer::class)
    class Flags(val backingList: List<Flag>) : List<Flag> by backingList {
        class Serializer : FlagsSerializer<Flag, Flags>(Flag::values, ::Flags)
    }
    enum class Flag {
        INSTANCE,
        JOIN,
        SPECTATE,
        JOIN_REQUEST,
        SYNC,
        PLAY
    }
}
