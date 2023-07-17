package app.titech.titechKyomuCore.http

import java.net.HttpCookie

interface HTTPClient {
    suspend fun send(request: HTTPRequest): String
    fun setCookie(cookie: HttpCookie)
}