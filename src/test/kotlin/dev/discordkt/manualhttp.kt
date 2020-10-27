
package dev.discordkt

import dev.discordkt.http.RouteExecutor
import dev.discordkt.http.routes.ChannelRoutes
import dev.discordkt.models.DiscordFile
import dev.discordkt.models.UserTest
import dev.discordkt.snowflake.Snowflake

import io.ktor.client.HttpClient
import io.ktor.client.features.addDefaultResponseValidation
import io.ktor.client.features.defaultRequest
import io.ktor.client.features.logging.DEFAULT
import io.ktor.client.features.logging.LogLevel
import io.ktor.client.features.logging.Logger
import io.ktor.client.features.logging.Logging
import io.ktor.client.request.header

import kotlinx.coroutines.runBlocking

import kotlinx.serialization.json.Json

fun main() = runBlocking {
    val token = System.getenv("TOKEN")!!
    val httpClient = HttpClient {
        addDefaultResponseValidation() // TODO custom response validation
        defaultRequest {
            header("Authorization", "Bot $token")
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
    }
    val json = Json {
        ignoreUnknownKeys = true
    }
    val executor = RouteExecutor(httpClient, json)


    println(executor.execute(ChannelRoutes.GET_CHANNEL(Snowflake.of(761287705354567680L))))
    println(
        executor.execute(
            ChannelRoutes.GET_CHANNEL_MESSAGES(
                Snowflake.of(761287705354567680L),
                around = Snowflake.of(761287705354567680L),
                limit = 2
            )
        )
    )
    println(
        executor.execute(
            ChannelRoutes.CREATE_MESSAGE(
                Snowflake.of(763132489467953162L),
                content = "MANUAL HTTP",
                file = DiscordFile("d.kt.png", UserTest::class.java.getResourceAsStream("/dktlogo.png").use {
                    it.readBytes()
                })
            )
        )
    )
}

