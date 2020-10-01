package io.github.romangraef.discordkt.http

import io.github.romangraef.discordkt.http.routes.RouteWithBody
import io.github.romangraef.discordkt.http.routes.RouteWithoutBody
import kotlinx.serialization.json.Json


fun httpExecute(method: String, url: String, body: String): String {
    TODO()
}

fun <RESULT> Json.execute(route: RouteWithoutBody<RESULT>) =
    this.decodeFromString(route.resultDeserializer, httpExecute(route.method, route.url, ""))

fun <RESULT, BODY> Json.execute(route: RouteWithBody<RESULT, BODY>, body: BODY) =
    this.decodeFromString(
        route.resultDeserializer,
        httpExecute(route.method, route.url, this.encodeToString(route.bodySerializer, body))
    )

