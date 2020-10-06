package io.github.romangraef.discordkt.models.guild

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GuildModifyIntegration(
    @SerialName("expire_behavior")
    val expireBehavior: IntegrationExpireBehavior,
    @SerialName("expire_grace_period")
    val expireGracePeriod: Int,
    @SerialName("enable_emoticons")
    val enableEmoticons: Boolean
)
