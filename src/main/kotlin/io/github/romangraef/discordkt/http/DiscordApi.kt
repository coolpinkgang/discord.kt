package io.github.romangraef.discordkt.http

import io.github.romangraef.discordkt.http.routes.ByteResultResponseMaker
import io.github.romangraef.discordkt.http.routes.JsonBodyRequestMaker
import io.github.romangraef.discordkt.http.routes.JsonResultResponseMaker
import io.github.romangraef.discordkt.http.routes.NoBodyRequestMaker
import io.github.romangraef.discordkt.http.routes.NoResultResponseMaker
import io.github.romangraef.discordkt.http.routes.ResponseMaker
import io.github.romangraef.discordkt.http.routes.Route
import io.ktor.client.HttpClient
import io.ktor.client.request.request
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.readBytes
import io.ktor.client.statement.readText
import kotlinx.serialization.json.Json

const val BASE_URL = "https://discord.com/api/v8"

class ApiIntermediary<RESULT : ResponseMaker>(val responseMaker: RESULT, val httpRequest: HttpResponse)

suspend fun <RESULT : ResponseMaker> makeNoBodyRequest(client: HttpClient, route: Route<RESULT, NoBodyRequestMaker>) =
    client.request<HttpResponse> {
        method = route.method
        url(BASE_URL + route.urlWithQuery)
    }

suspend fun <RESULT : ResponseMaker, T> makeJsonBodyRequest(
    client: HttpClient,
    json: Json,
    route: Route<RESULT, JsonBodyRequestMaker<T>>,
    obj: T
) = client.request<HttpResponse> {
    method = route.method
    url(BASE_URL + route.urlWithQuery)
    body = json.encodeToString(route.requestMaker.serializer, obj)
}

suspend fun <RESULT : ResponseMaker> Route<RESULT, NoBodyRequestMaker>.request(
    client: HttpClient,
) = ApiIntermediary(this.responseMaker, makeNoBodyRequest(client, this))

suspend fun <RESULT : ResponseMaker, T> Route<RESULT, JsonBodyRequestMaker<T>>.request(
    client: HttpClient, json: Json, obj: T
) = ApiIntermediary(this.responseMaker, makeJsonBodyRequest(client, json, this, obj))

@JvmName("getNoResult")
suspend fun ApiIntermediary<NoResultResponseMaker>.get() {
    this.httpRequest.readText()
}

suspend fun <T> ApiIntermediary<JsonResultResponseMaker<T>>.get(json: Json) =
    json.decodeFromString(this.responseMaker.serializer, this.httpRequest.readText())

@JvmName("getByteResult")
suspend fun ApiIntermediary<ByteResultResponseMaker>.get() =
    this.httpRequest.readBytes()
