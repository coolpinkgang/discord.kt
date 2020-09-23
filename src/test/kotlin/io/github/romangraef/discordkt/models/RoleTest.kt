package io.github.romangraef.discordkt.models

import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object RoleTest : Spek({
    describe("Role") {
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
        it("deserializes correctly") {
            val role = json.decodeFromString<Role>(roleJsonString)
            assert(role.longId == 41771983423143936L) { "Invalid id" }
            assert(role.name == "WE DEM BOYZZ!!!!!!") { "Invalid name" }
        }
    }
})
