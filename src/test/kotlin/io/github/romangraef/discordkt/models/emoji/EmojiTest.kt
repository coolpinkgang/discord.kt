package io.github.romangraef.discordkt.models.emoji

import io.github.romangraef.discordkt.models.emoji.Emoji
import io.github.romangraef.discordkt.models.serial.Snowflake
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.containExactly
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class EmojiTest : StringSpec({
    "should parse" {
        val json = Json {}
        val emojiJsonText = """
            {
              "id": "41771983429993937",
              "name": "LUL",
              "roles": ["41771983429993000", "41771983429993111"],
              "user": {
                "username": "Luigi",
                "discriminator": "0002",
                "id": "96008815106887111",
                "avatar": "5500909a3274e1812beb4e8de6631111"
              },
              "require_colons": true,
              "managed": false,
              "animated": false
            }
        """
        val emoji = json.decodeFromString<Emoji>(emojiJsonText)
        emoji.longId shouldBe 41771983429993937L
        emoji.name shouldBe "LUL"
        emoji.roles should containExactly(
            Snowflake("41771983429993000"), Snowflake("41771983429993111")
        )
        emoji.user!!.username shouldBe "Luigi"
        emoji.user!!.discriminator shouldBe "0002"
        emoji.user!!.longId shouldBe 96008815106887111L
        emoji.user!!.avatarHash shouldBe "5500909a3274e1812beb4e8de6631111"
        emoji.requireColons shouldBe true
        emoji.managed shouldBe false
        emoji.animated shouldBe false
    }
})
