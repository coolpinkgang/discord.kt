@file:Suppress("FunctionName")

package io.github.romangraef.discordkt.http.routes

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.http.HttpMethod
import io.ktor.http.encodeURLQueryComponent
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

sealed class RequestMaker<BODY>
object NoBodyRequestMaker : RequestMaker<Unit>()
class JsonBodyRequestMaker<BODY>(val serializer: KSerializer<BODY>) : RequestMaker<BODY>()
class CustomRequestMaker<BODY>(val updateRequest: HttpRequestBuilder.(BODY, Json) -> Unit) : RequestMaker<BODY>()
sealed class ResponseMaker<RESUlt>
object NoResultResponseMaker : ResponseMaker<Unit>()
class JsonResultResponseMaker<RESULT>(val serializer: KSerializer<RESULT>) : ResponseMaker<RESULT>()
object ByteResultResponseMaker : ResponseMaker<ByteArray>()
class Route<RESULT, BODY>(
    val method: HttpMethod,
    val url: String,
    val responseMaker: ResponseMaker<RESULT>,
    val requestMaker: RequestMaker<BODY>,
) {
    val queryParam = mutableMapOf<String, String>()

    fun query(name: String, value: Any?) {
        if (value != null)
            queryParam[name] = value.toString()
    }

    val urlWithQuery
        get() =
            if (queryParam.isEmpty()) url
            else "$url?" + queryParam.entries.joinToString("&") { (key, value) ->
                key.encodeURLQueryComponent() + "=" + value.encodeURLQueryComponent()
            }

}


operator fun <T : Route<RESULT, BODY>, RESULT, BODY> T.invoke(block: T.() -> Unit): T {
    block()
    return this
}

@Suppress("UNCHECKED_CAST")
inline fun <reified OUTPUT, reified BODY> REQUEST(method: HttpMethod, url: String): Route<OUTPUT, BODY> = Route(
    method, url,
    if (Unit is OUTPUT) NoResultResponseMaker as ResponseMaker<OUTPUT>
    else JsonResultResponseMaker(serializer()),
    if (Unit is BODY) NoBodyRequestMaker as RequestMaker<BODY>
    else JsonBodyRequestMaker(serializer())
)

inline fun <reified OUTPUT, reified BODY> POST(url: String) =
    REQUEST<OUTPUT, BODY>(HttpMethod.Post, url)

inline fun <reified OUTPUT> GET(url: String) =
    REQUEST<OUTPUT, Unit>(HttpMethod.Get, url)

inline fun <reified OUTPUT, reified BODY> PUT(url: String) =
    REQUEST<OUTPUT, BODY>(HttpMethod.Put, url)

inline fun <reified OUTPUT, reified BODY> PATCH(url: String) =
    REQUEST<OUTPUT, BODY>(HttpMethod.Patch, url)

inline fun <reified OUTPUT> DELETE(url: String) =
    REQUEST<OUTPUT, Unit>(HttpMethod.Delete, url)
