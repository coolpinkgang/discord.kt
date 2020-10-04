package io.github.romangraef.discordkt.models.channels

import io.github.romangraef.discordkt.models.channel.Message
import io.github.romangraef.discordkt.models.channel.MessageType
import io.github.romangraef.discordkt.models.deserializes
import io.github.romangraef.discordkt.models.user.UserFlag
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.beEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import java.time.OffsetDateTime
import java.time.ZoneOffset

class MessageTests : StringSpec({
    "Normal Message" {
        """
        {
            "id": "761996835337928715",
            "type": 0,
            "content": "This is a message",
            "channel_id": "761287705354567680",
            "author": {
                "id": "310702108997320705",
                "username": "romangraef89",
                "avatar": "f1fb2bd7862ba7153b98c94bbdb7e750",
                "discriminator": "0998",
                "public_flags": 131200
            },
            "attachments": [],
            "embeds": [],
            "mentions": [],
            "mention_roles": [],
            "pinned": false,
            "mention_everyone": false,
            "tts": false,
            "timestamp": "2020-10-03T17:03:22.761000+00:00",
            "edited_timestamp": null,
            "flags": 0
        }
        """.trimIndent().deserializes<Message> {
            it.longId shouldBe 761996835337928715L
            it.type shouldBe MessageType.DEFAULT
            it.content shouldBe "This is a message"
            it.channelId shouldBe 761287705354567680L
            it.author.longId shouldBe 310702108997320705L
            it.author.username shouldBe "romangraef89"
            it.author.avatarHash shouldBe "f1fb2bd7862ba7153b98c94bbdb7e750"
            it.author.discriminator shouldBe "0998"
            it.author.publicFlags shouldContainExactly setOf(UserFlag.HOUSE_BRILLIANCE, UserFlag.VERIFIED_BOT_DEVELOPER)
            it.attachments should beEmpty()
            it.embeds should beEmpty()
            it.mentions should beEmpty()
            it.pinned shouldBe false
            it.mentionEveryone shouldBe false
            it.tts shouldBe false
            it.timestamp shouldBe OffsetDateTime.of(
                2020, 10, 3,
                17, 3, 22, 761000, ZoneOffset.ofHours(0)
            )
            it.editedTimestamp shouldBe null
            it.flags!! should beEmpty()
        }
    }
})
