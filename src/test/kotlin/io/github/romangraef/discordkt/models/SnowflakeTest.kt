package io.github.romangraef.discordkt.models

import io.github.romangraef.discordkt.models.serial.Snowflake
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import java.time.OffsetDateTime
import kotlin.random.Random
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.encodeToJsonElement

class SnowflakeTest : StringSpec({
    "Snowflake Serialization" {
        val json = Json {}
        val longValue = Random.nextLong()
        val snowflake = Snowflake.of(longValue.toString())
        snowflake.longId shouldBe longValue
        val serialResult = json.encodeToJsonElement(snowflake)
        assert(serialResult is JsonPrimitive && serialResult.isString) { "$serialResult should be a json primitve string" }
        json.decodeFromString<Snowflake>(json.encodeToString(snowflake)) shouldBe snowflake
    }
    "Snowflake constructors" {
        val sf = Snowflake.of(194093402659225600L)
        val otherSf = Snowflake.of(
            sf.asOffsetDateTime,
            sf.workerId,
            sf.processId,
            sf.incrementId
        )
        sf shouldBe otherSf
    }
})
