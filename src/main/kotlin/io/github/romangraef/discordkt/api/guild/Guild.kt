package io.github.romangraef.discordkt.api.guild

import io.github.romangraef.discordkt.api.ApiModel
import io.github.romangraef.discordkt.api.DiscordKt
import io.github.romangraef.discordkt.api.User
import io.github.romangraef.discordkt.api.channel.BaseChannel
import io.github.romangraef.discordkt.api.channel.GuildChannel
import io.github.romangraef.discordkt.api.channel.TextChannel
import io.github.romangraef.discordkt.api.channel.VoiceChannel
import io.github.romangraef.discordkt.api.emoji.Emoji
import io.github.romangraef.discordkt.api.permission.Role
import io.github.romangraef.discordkt.api.voice.VoiceState
import io.github.romangraef.discordkt.models.gateway.PresenceUpdate
import io.github.romangraef.discordkt.models.guild.*
import io.github.romangraef.discordkt.models.guild.Guild
import io.github.romangraef.discordkt.models.permissions.Permission
import io.github.romangraef.discordkt.snowflake.Snowflake
import io.github.romangraef.discordkt.snowflake.SnowflakeMixin
import java.time.OffsetDateTime

class Guild(
    override val discordKt: DiscordKt,
    override val id: Snowflake,
    val name: String,
    val icon: String?,
    val splash: String?,
    val discoverySplash: String?,
    val owner: User,
    val permissions: List<Permission>,
    val region: String,
    val afkChannel: VoiceChannel,
    val afkTimeout: Int,
    val widgetEnabled: Boolean,
    val widgetChannel: GuildChannel,
    val verificationLevel: VerificationLevel,
    val defaultMessageNotificationsLevel: DefaultMessageNotificationsLevel,
    val explicitContentFilterLevel: ExplicitContentFilterLevel,
    val roles: List<Role>,
    val emojis: List<Emoji>,
    val features: List<GuildFeature>,
    val mfaLevel: MfaLevel,
    val systemChannel: TextChannel,
    val systemChannelFlags: List<SystemChannelFlag>,
    val rulesChannel: TextChannel,
    val members: List<Member>,
    val channels: List<BaseChannel>,
    val description: String?,
    val banner: String?,
    val premiumTier: PremiumTier,
    val premiumSubscriptionCount: Int,
    val preferredLocale: String,
    val publicUpdatesChannel: TextChannel
) : SnowflakeMixin, ApiModel {
    companion object {
        fun of(guild: Guild, guildChannels: List<GuildChannel>, guildMembers: List<Member>): io.github.romangraef.discordkt.api.guild.Guild = TODO()
    }
}
