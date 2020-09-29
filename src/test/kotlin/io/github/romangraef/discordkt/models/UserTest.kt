package io.github.romangraef.discordkt.models

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.collections.containExactly
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

object UserTest : StringSpec({
    "User Serialization" {
        val json = Json {
            ignoreUnknownKeys = true
        }
        val userJson =
            """{"id":"80351110224678912","username":"Nelly","discriminator":"1337","avatar":"8342729096ea3675442027381ff50dfe","verified":true,"email":"nelly@discord.com","flags":64,"premium_type":1,"public_flags":64}"""
        val user = json.decodeFromString<User>(userJson)
        user.longId shouldBe 80351110224678912L
        user.username shouldBe "Nelly"
        user.discriminator shouldBe "1337"
        user.avatarHash shouldBe "8342729096ea3675442027381ff50dfe"
        user.verified shouldBe true
        user.email shouldBe "nelly@discord.com"

        user.flags should containExactly(User.Flag.HOUSE_BRAVERY)
        user.publicFlags should containExactly(User.Flag.HOUSE_BRAVERY)

        user.premiumType shouldBe User.PremiumType.NITRO_CLASSIC
    }
}
)
