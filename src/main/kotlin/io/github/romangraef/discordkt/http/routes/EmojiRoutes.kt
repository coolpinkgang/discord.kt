package io.github.romangraef.discordkt.http.routes

import io.github.romangraef.discordkt.models.emoji.CreateGuildEmoji
import io.github.romangraef.discordkt.models.emoji.Emoji
import io.github.romangraef.discordkt.models.emoji.ModifyGuildEmoji
import io.github.romangraef.discordkt.snowflake.Snowflake

object EmojiRoutes {
    fun LIST_GUILD_EMOJIS(guildId: Snowflake) =
        GET<List<Emoji>>("/guilds/$guildId/emojis")
    fun GET_GUILD_EMOJI(guildId: Snowflake, emojiId: Snowflake) =
        GET<Emoji>("/guilds/$guildId/emojis/$emojiId")
    fun CREATE_GUILD_EMOJI(guildId: Snowflake) =
        POST<Emoji, CreateGuildEmoji>("/guilds/$guildId/emojis")
    fun MODIFY_GUILD_EMOJI(guildId: Snowflake, emojiId: Snowflake) =
        PATCH<Emoji, ModifyGuildEmoji>("/guilds/$guildId/emojis/$emojiId")
    fun DELETE_GUILD_EMOJI(guildId: Snowflake, emojiId: Snowflake) =
        DELETE<Unit>("/guilds/$guildId/emojis/$emojiId")
}
