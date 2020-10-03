@file:Suppress("FunctionName")

package io.github.romangraef.discordkt.http.routes


import io.ktor.http.HttpMethod
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer

sealed class Route<RESULT>(
    val method: HttpMethod,
    val url: String,
    val resultDeserializer: KSerializer<RESULT>,
)

class RouteWithoutBody<RESULT>(
    method: HttpMethod,
    url: String,
    resultDeserializer: KSerializer<RESULT>,
) : Route<RESULT>(method, url, resultDeserializer)

class RouteWithBody<RESULT, BODY>(
    method: HttpMethod,
    url: String,
    resultDeserializer: KSerializer<RESULT>,
    val bodySerializer: KSerializer<BODY>,
) : Route<RESULT>(method, url, resultDeserializer)


inline fun <reified OUTPUT, reified BODY> POST(url: String) =
    RouteWithBody(HttpMethod.Post, url, serializer<OUTPUT>(), serializer<BODY>())
inline fun <reified OUTPUT> GET(url: String) = RouteWithoutBody(HttpMethod.Get, url, serializer<OUTPUT>())
inline fun <reified OUTPUT, reified BODY> PUT(url: String) =
    RouteWithBody(HttpMethod.Put, url, serializer<OUTPUT>(), serializer<BODY>())
inline fun <reified OUTPUT, reified BODY> PATCH(url: String) =
    RouteWithBody(HttpMethod.Patch, url, serializer<OUTPUT>(), serializer<BODY>())
inline fun <reified OUTPUT> DELETE(url: String) = RouteWithoutBody(HttpMethod.Delete, url, serializer<OUTPUT>())
