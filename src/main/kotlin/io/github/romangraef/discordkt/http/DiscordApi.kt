package io.github.romangraef.discordkt.http

import io.github.romangraef.discordkt.http.routes.RouteWithBody
import io.github.romangraef.discordkt.http.routes.RouteWithoutBody
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

suspend fun HttpClient.execute(method: HttpMethod, url: String, body: String): String = this.request(url) {
    this.method = method
    this.body = body
}

suspend fun <RESULT> HttpClient.execute(json: Json, route: RouteWithoutBody<RESULT>) =
    json.decodeFromString(route.resultDeserializer, execute(route.method, route.url, ""))

suspend fun <RESULT, BODY> HttpClient.execute(json: Json, route: RouteWithBody<RESULT, BODY>, body: BODY) =
    json.decodeFromString(
        route.resultDeserializer,
        execute(route.method, route.url, json.encodeToString(route.bodySerializer, body))
    )
