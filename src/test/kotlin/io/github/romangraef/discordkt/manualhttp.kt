
package io.github.romangraef.discordkt

import io.github.romangraef.discordkt.http.RouteExecutor
import io.github.romangraef.discordkt.http.routes.ChannelRoutes
import io.github.romangraef.discordkt.models.serial.Snowflake
import io.ktor.client.HttpClient
import io.ktor.client.features.*
import io.ktor.client.request.*
import kotlinx.serialization.json.Json
import kotlinx.coroutines.*

fun main() = runBlocking {
    val token = System.getenv("TOKEN")!!
    val httpClient = HttpClient {
        addDefaultResponseValidation() // TODO custom response validation
        defaultRequest {
            header("Authorization", "Bot $token")
        }
    }
    val json = Json {
        ignoreUnknownKeys = true
    }
    val executor = RouteExecutor(httpClient, json)


    println(executor.execute(ChannelRoutes.GET_CHANNEL(Snowflake.of(761287705354567680L))))
    println(executor.execute(ChannelRoutes.GET_CHANNEL_MESSAGES(Snowflake.of(761287705354567680L), around = Snowflake.of(761287705354567680L))))
}

