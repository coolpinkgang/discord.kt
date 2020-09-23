package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.serial.Snowflake
import kotlin.random.Random
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.encodeToJsonElement
import org.spekframework.spek2.Spek
import org.spekframework.spek2.style.specification.describe

object SnowflakeTest : Spek({
    describe("Snowflake Serialization") {
        val json = Json {}
        val longValue = Random.nextLong()
        val snowflake = Snowflake(longValue.toString())
        describe("state") {
            it("has the correct long value") {
                assert(snowflake.longId == longValue)
            }
        }
        describe("serialization") {
            it("serializes into a string") {
                val serialResult = json.encodeToJsonElement(snowflake)
                assert(serialResult is JsonPrimitive && serialResult.isString)
            }
            it("decodes correct after serialization") {
                json.decodeFromString<Snowflake>(json.encodeToString(snowflake))
            }
        }
    }
})
