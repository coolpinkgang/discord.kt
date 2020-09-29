package io.github.romangraef.discordkt.models

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object UserTest : Spek({
    describe("User Serialization") {
        val json = Json {
            ignoreUnknownKeys = true
        }
        val userJson = """{"id":"80351110224678912","username":"Nelly","discriminator":"1337","avatar":"8342729096ea3675442027381ff50dfe","verified":true,"email":"nelly@discord.com","flags":64,"premium_type":1,"public_flags":64}"""
        it("deserializes correctly") {
            val user = json.decodeFromString<User>(userJson)
            assert(user.longId == 80351110224678912L)
            assert(user.username == "Nelly")
            assert(user.discriminator == "1337")
            assert(user.avatarHash == "8342729096ea3675442027381ff50dfe")
            assert(user.verified == true)
            assert(user.email == "nelly@discord.com")
            assert(user.flags.size == 1)
            assert(user.flags[0] == User.Flag.HOUSE_BRAVERY)
            assert(user.publicFlags.size == 1)
            assert(user.flags[0] == User.Flag.HOUSE_BRAVERY)
            assert(user.premiumType == User.PremiumType.NITRO_CLASSIC)
        }
    }
})
