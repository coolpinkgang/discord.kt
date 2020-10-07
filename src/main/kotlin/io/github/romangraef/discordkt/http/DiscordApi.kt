package io.github.romangraef.discordkt.http

import io.github.romangraef.discordkt.http.routes.*
import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import okhttp3.RequestBody

const val BASE_URL = "https://discord.com/api/v8"

class RouteExecutor(val client: HttpClient, val json: Json) {
    @JvmName("executeWithoutResultOrBody")
    suspend fun execute(route: Route<NoResultResponseMaker, NoBodyRequestMaker>): Unit =
        client.request<HttpResponse> {
            method = route.method
            url(BASE_URL + route.urlWithQuery)
        }.let { if (it.status != HttpStatusCode.NoContent) throw HttpRequestException(route, it) }
    @JvmName("executeJsonWithoutBody")
    suspend fun <RESULT> execute(route: Route<JsonResultResponseMaker<RESULT>, NoBodyRequestMaker>): RESULT =
        json.decodeFromString(route.responseMaker.serializer, client.request<HttpResponse> {
            method = route.method
            url(BASE_URL + route.urlWithQuery)
        }.also { if (it.status != HttpStatusCode.OK) throw HttpRequestException(route, it) }.readText())
    @JvmName("executeJson")
    suspend fun <RESULT, REQUEST> execute(route: Route<JsonResultResponseMaker<RESULT>, JsonBodyRequestMaker<REQUEST>>, requestBody: REQUEST): RESULT =
        json.decodeFromString(route.responseMaker.serializer, client.request<HttpResponse> {
            method = route.method
            url(BASE_URL + route.urlWithQuery)
            body = json.encodeToString(route.requestMaker.serializer, requestBody)
        }.also { if (it.status != HttpStatusCode.OK) throw HttpRequestException(route, it) }.readText())
    @JvmName("executeByteArray")
    suspend fun <REQUEST> execute(route: Route<ByteResultResponseMaker, JsonBodyRequestMaker<REQUEST>>, requestBody: REQUEST): ByteArray =
        client.request<HttpResponse> {
            method = route.method
            url(BASE_URL + route.urlWithQuery)
            body = json.encodeToString(route.requestMaker.serializer, requestBody)
        }.also { if (it.status != HttpStatusCode.OK) throw HttpRequestException(route, it) }.readBytes()
    @JvmName("executeByteArrayWithoutBody")
    suspend fun execute(route: Route<ByteResultResponseMaker, NoBodyRequestMaker>): ByteArray =
        client.request<HttpResponse> {
            method = route.method
            url(BASE_URL + route.urlWithQuery)
        }.also { if (it.status != HttpStatusCode.OK) throw HttpRequestException(route, it) }.readBytes()
    @JvmName("executeWithoutResult")
    suspend fun <REQUEST> execute(route: Route<NoResultResponseMaker, JsonBodyRequestMaker<REQUEST>>, requestBody: REQUEST): Unit =
        client.request<HttpResponse> {
            method = route.method
            url(BASE_URL + route.urlWithQuery)
            body = json.encodeToString(route.requestMaker.serializer, requestBody)
        }.let { if (it.status != HttpStatusCode.NoContent) throw HttpRequestException(route, it) }
}

class HttpRequestException(route: Route<*, *>, httpResponse: HttpResponse) :
    RuntimeException("A http request failed to '${route.url}' with status: ${httpResponse.status}")

/*

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
*/
