package app.titech.titechKyomuCore.http

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpCookie
import java.net.HttpURLConnection
import java.net.URL

class HTTPClientImpl: HTTPClient {
    private var cookies = mutableSetOf<HttpCookie>()

    override suspend fun send(request: HTTPRequest): String = withContext(Dispatchers.IO) {
        var urlString = request.baseURL + request.path
        request.queryParameters?.run {
            urlString += "?" + this.map { "${it.key}=${it.value}" }.joinToString("&")
        }
        val url = URL(urlString)

        var connection = generateUrlConnection(url, request.httpMethod, request.headerFields, cookies)

        do {
            println("RequestURL: " + connection.url.toString())
            println("Method: " + connection.requestMethod.toString())
            println("RequestHeaders: " + connection.requestProperties.toString())
            connection.connect()

            println("responseHeaders: " + connection.headerFields.toString())
            println("responseCode: " + connection.responseCode.toString())

            val setCookie = connection.headerFields["Set-Cookie"] ?: connection.headerFields["Set-cookie"]
            setCookie
                ?.flatMap {
                    HttpCookie.parse(it)
                }?.filter {
                    !it.hasExpired()
                }?.forEach { cookie ->
                    // FIXME: n^2
                    if (cookie.domain == null) {
                        cookie.domain = connection.url.host
                    }
                    val sameNameCookie = cookies.firstOrNull { it.name == cookie.name }

                    if (sameNameCookie != null) {
                        cookies.remove(sameNameCookie)
                    }
                    cookies.add(cookie)
                }

            var needRedirect = false
            if (connection.responseCode in 300..399) {
                val location = connection.getHeaderField("Location") ?: connection.getHeaderField("location")
                try {
                    val locationURL = when {
                        location.startsWith("?") -> URL(url.protocol + "://" + url.host + url.path + location)
                        location.startsWith("/") -> URL(url.protocol + "://" + url.host + location)
                        else -> URL(location)
                    }
                    connection =
                        generateUrlConnection(locationURL, "GET", request.headerFields, cookies)
                    needRedirect = true
                } catch (e: Exception) {
                    needRedirect = false
                }
            }
        } while (needRedirect)

        val br = BufferedReader(InputStreamReader(connection.inputStream))

        val sb = StringBuilder()

        for (line in br.readLines()) {
            sb.append(line)
        }

        br.close()
        connection.inputStream.close()

        sb.toString()
    }

    override fun setCookie(cookie: HttpCookie) {
        this.cookies = mutableSetOf(cookie)
    }

    private fun generateUrlConnection(
        url: URL,
        httpMethod: String,
        headerFields: Map<String, String>?,
        cookies: Set<HttpCookie>
    ): HttpURLConnection {
        val connection = url.openConnection() as HttpURLConnection

        headerFields?.forEach {
            connection.setRequestProperty(it.key, it.value)
        }
        connection.setRequestProperty(
            "Cookie",
            cookies.map { "${it.name}=${it.value}" }.joinToString("; ")
        )
        connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Linux; Android 12; Pixel 7 XL) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/111.0.5563.57 Mobile Safari/537.36")
        connection.requestMethod = httpMethod
        connection.instanceFollowRedirects = false

        return connection
    }
}