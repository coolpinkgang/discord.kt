package io.github.romangraef.discordkt.http

import io.github.romangraef.discordkt.http.routes.ByteResultResponseMaker
import io.github.romangraef.discordkt.http.routes.JsonBodyRequestMaker
import io.github.romangraef.discordkt.http.routes.JsonResultResponseMaker
import io.github.romangraef.discordkt.http.routes.NoBodyRequestMaker
import io.github.romangraef.discordkt.http.routes.NoResultResponseMaker
import io.github.romangraef.discordkt.http.routes.Route
import io.ktor.client.HttpClient
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readBytes
import io.ktor.client.statement.readText
import io.ktor.http.HttpStatusCode
import io.ktor.http.isSuccess
import kotlinx.serialization.json.Json

const val BASE_URL = "https://discord.com/api/v8"

class RouteExecutor(val client: HttpClient, val json: Json) {

    suspend fun <RESULT> execute(route: Route<RESULT, Unit>): RESULT = execute(route, Unit)

    @Suppress("UNCHECKED_CAST")
    suspend fun <RESULT, BODY> execute(route: Route<RESULT, BODY>, `object`: BODY): RESULT =
        client.request<HttpResponse> {
            method = route.method
            url(BASE_URL + route.urlWithQuery)
            when (route.requestMaker) {
                is NoBodyRequestMaker -> Unit
                is JsonBodyRequestMaker<BODY> -> body = json.encodeToString(route.requestMaker.serializer, `object`)
            }
        }.also { if (!it.status.isSuccess()) throw HttpRequestException(route, it) }.let {
            when (route.responseMaker) {
                is JsonResultResponseMaker -> json.decodeFromString(route.responseMaker.serializer, it.readText())
                is ByteResultResponseMaker -> it.readBytes() as RESULT
                is NoResultResponseMaker -> Unit as RESULT
            }
        }

}

class HttpRequestException(route: Route<*, *>, httpResponse: HttpResponse) :
    RuntimeException("A http request failed to '${route.url}' with status: ${httpResponse.status}")
