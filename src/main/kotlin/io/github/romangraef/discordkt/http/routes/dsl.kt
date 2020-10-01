@file:Suppress("FunctionName")

package io.github.romangraef.discordkt.http.routes


import io.github.romangraef.discordkt.models.Channel
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer

sealed class Route<RESULT>(
    val method: String,
    val url: String,
    val resultDeserializer: KSerializer<RESULT>,
)

class RouteWithoutBody<RESULT>(
    method: String,
    url: String,
    resultDeserializer: KSerializer<RESULT>,
) : Route<RESULT>(method, url, resultDeserializer)

class RouteWithBody<RESULT, BODY>(
    method: String,
    url: String,
    resultDeserializer: KSerializer<RESULT>,
    val bodySerializer: KSerializer<BODY>,
) : Route<RESULT>(method, url, resultDeserializer)


inline fun <reified OUTPUT> GET(url: String) = RouteWithoutBody("GET", url, serializer<OUTPUT>())
inline fun <reified OUTPUT, reified BODY> PATCH(url: String) =
    RouteWithBody("PATCH", url, serializer<OUTPUT>(), serializer<BODY>())
