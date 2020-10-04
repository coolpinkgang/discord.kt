package io.github.romangraef.discordkt.http

import io.github.romangraef.discordkt.http.routes.RouteWithBody
import io.github.romangraef.discordkt.http.routes.RouteWithoutBody
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readText
import io.ktor.http.*
import kotlinx.serialization.json.Json

const val BASE_URL = "https://discord.com/api/v8"

suspend fun HttpClient.execute(method: HttpMethod, url: String, body: String): String = this.request<HttpResponse>("$BASE_URL$url") {
    this.method = method
    this.body = body
}.readText()

suspend fun <RESULT> HttpClient.execute(json: Json, route: RouteWithoutBody<RESULT>) =
    json.decodeFromString(route.resultDeserializer, execute(route.method, route.urlWithQuery, ""))

suspend fun <RESULT, BODY> HttpClient.execute(json: Json, route: RouteWithBody<RESULT, BODY>, body: BODY) =
    json.decodeFromString(
        route.resultDeserializer,
        execute(route.method, route.urlWithQuery, json.encodeToString(route.bodySerializer, body))
    )
