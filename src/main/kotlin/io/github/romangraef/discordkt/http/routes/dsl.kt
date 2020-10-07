@file:Suppress("FunctionName")

package io.github.romangraef.discordkt.http.routes

import io.ktor.http.HttpMethod
import io.ktor.http.encodeURLQueryComponent
import kotlinx.serialization.KSerializer
import kotlinx.serialization.serializer

sealed class RequestMaker
object NoBodyRequestMaker : RequestMaker()
class JsonBodyRequestMaker<BODY>(val serializer: KSerializer<BODY>) : RequestMaker()
sealed class ResponseMaker
object NoResultResponseMaker : ResponseMaker()
class JsonResultResponseMaker<RESULT>(val serializer: KSerializer<RESULT>) : ResponseMaker()
object ByteResultResponseMaker : ResponseMaker()
class Route<RESULT : ResponseMaker, BODY : RequestMaker>(
    val method: HttpMethod,
    val url: String,
    val responseMaker: RESULT,
    val requestMaker: BODY,
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

inline fun <reified OUTPUT, reified BODY> POST(url: String) =
    Route(HttpMethod.Post, url, JsonResultResponseMaker(serializer<BODY>()), JsonBodyRequestMaker(serializer<OUTPUT>()))

inline fun <reified OUTPUT> GET(url: String) =
    Route(HttpMethod.Get, url, JsonResultResponseMaker(serializer<OUTPUT>()), NoBodyRequestMaker)

inline fun <reified OUTPUT, reified BODY> PUT(url: String) =
    Route(HttpMethod.Put, url, JsonResultResponseMaker(serializer<OUTPUT>()), JsonBodyRequestMaker(serializer<BODY>()))

inline fun <reified OUTPUT, reified BODY> PATCH(url: String) =
    Route(HttpMethod.Patch, url, JsonResultResponseMaker(serializer<OUTPUT>()), JsonBodyRequestMaker(serializer<BODY>()))

inline fun <reified OUTPUT> DELETE(url: String) =
    Route(HttpMethod.Delete, url, JsonResultResponseMaker(serializer<OUTPUT>()), NoBodyRequestMaker)
