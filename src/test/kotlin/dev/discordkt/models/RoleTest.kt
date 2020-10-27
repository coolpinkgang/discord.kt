package dev.discordkt.models

import dev.discordkt.models.permissions.Role

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class RoleTest : StringSpec({
    "Role" {
        val json = Json {
            ignoreUnknownKeys = true
        }
        val roleJsonString = """
{
  "id": "41771983423143936",
  "name": "WE DEM BOYZZ!!!!!!",
  "color": 3447003,
  "hoist": true,
  "position": 1,
  "permissions": 66321471,
  "permissions_new": "66321471",
  "managed": false,
  "mentionable": false
}"""
            val role = json.decodeFromString<Role>(roleJsonString)
            role.longId shouldBe 41771983423143936L
            role.name shouldBe "WE DEM BOYZZ!!!!!!"
    }
})
