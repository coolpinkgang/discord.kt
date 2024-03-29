package dev.discordkt.models.channels

import dev.discordkt.models.channel.Channel
import dev.discordkt.models.channel.ChannelType
import dev.discordkt.models.channel.Overwrite
import dev.discordkt.models.channel.OverwriteType
import dev.discordkt.models.deserializes
import dev.discordkt.models.permissions.Permission
import dev.discordkt.snowflake.Snowflake

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.beEmpty
import io.kotest.matchers.collections.containExactly
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class TextChannelTests : StringSpec({
    "Text Channel With Perms" {
        """{
  "id": "761287705354567680",
  "last_message_id": "762021898447487038",
  "type": 0,
  "name": "githook",
  "position": 21,
  "parent_id": null,
  "topic": null,
  "guild_id": "455265814191276037",
  "permission_overwrites": [],
  "nsfw": false,
  "rate_limit_per_user": 0
}
""".deserializes<Channel> {
            it.longId shouldBe 761287705354567680L
            it.lastMessageId?.longId shouldBe 762021898447487038L
            it.type shouldBe ChannelType.GUILD_TEXT
            it.name shouldBe "githook"
            it.position shouldBe 21
            it.parentId shouldBe null
            it.topic shouldBe null
            it.guildId?.longId shouldBe 455265814191276037L
            it.permissionOverwrites!! should beEmpty()
            it.nsfw shouldBe false
            it.rateLimitPerUser shouldBe 0
        }
    }
    "Overwrite channel"{
        """{
  "id": "479635335760969748",
  "last_message_id": "504016397509328908",
  "type": 0,
  "name": "test-cazgirl",
  "position": 4,
  "parent_id": "455265814191276038",
  "topic": null,
  "guild_id": "455265814191276037",
  "permission_overwrites": [
    {
      "id": "455265814191276037",
      "type": "role",
      "allow": "0",
      "deny": "1024"
    },
    {
      "id": "467792830786699286",
      "type": "role",
      "allow": "1024",
      "deny": "0"
    }
  ],
  "nsfw": false,
  "rate_limit_per_user": 0
}""".deserializes<Channel> {
            it.longId shouldBe 479635335760969748L
            it.lastMessageId?.longId shouldBe 504016397509328908
            it.type shouldBe ChannelType.GUILD_TEXT
            it.name shouldBe "test-cazgirl"
            it.position shouldBe 4
            it.parentId?.longId shouldBe 455265814191276038L
            it.topic shouldBe null
            it.guildId?.longId shouldBe 455265814191276037L
            it.permissionOverwrites should containExactly(
                Overwrite(
                    Snowflake.of(455265814191276037L),
                    OverwriteType.ROLE, Permission.BitField(listOf()), Permission.BitField(listOf(Permission.VIEW_CHANNEL))),
                Overwrite(
                    Snowflake.of(467792830786699286L),
                    OverwriteType.ROLE, Permission.BitField(listOf(Permission.VIEW_CHANNEL)), Permission.BitField(listOf())
                )
            )
            it.nsfw shouldBe false
            it.rateLimitPerUser shouldBe 0
        }
    }
})
