package io.github.romangraef.discordkt.models

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.beEmpty
import io.kotest.matchers.collections.containExactly
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe

class UserTest : StringSpec({
    "User Serialization" {
        """
{
  "id": "80351110224678912",
  "username": "Nelly",
  "discriminator": "1337",
  "avatar": "8342729096ea3675442027381ff50dfe",
  "verified": true,
  "email": "nelly@discord.com",
  "flags": 64,
  "premium_type": 1,
  "public_flags": 64
}
        """.deserializes<User> {
            it.longId shouldBe 80351110224678912L
            it.username shouldBe "Nelly"
            it.discriminator shouldBe "1337"
            it.avatarHash shouldBe "8342729096ea3675442027381ff50dfe"
            it.verified shouldBe true
            it.email shouldBe "nelly@discord.com"

            it.flags should containExactly(User.Flag.HOUSE_BRAVERY)
            it.publicFlags should containExactly(User.Flag.HOUSE_BRAVERY)
            it.premiumType shouldBe User.PremiumType.NITRO_CLASSIC

        }
        """{"id": "364009534722801665", "username": "Postfix-Bot", "avatar": "8957c49f91481c041d46951a665f8c60", "discriminator": "9450", "public_flags": 0, "flags": 0, "bot": true, "email": null, "verified": true, "locale": "en-US", "mfa_enabled": true}""".deserializes<User> {
            it.longId shouldBe 364009534722801665L
            it.username shouldBe "Postfix-Bot"
            it.avatarHash shouldBe "8957c49f91481c041d46951a665f8c60"
            it.publicFlags should beEmpty()
            it.bot shouldBe true
            it.locale shouldBe "en-US"
            it.verified shouldBe true
            it.email shouldBe null
            it.discriminator shouldBe "9450"
        }
    }
}
)
