package app.titech.titechKyomuCore.http

class ReportCheckPageRequest: HTTPRequest {
    override val baseURL: String = BaseURL.origin
    override val httpMethod: String = "GET"
    override val path: String = "/Titech/Student/%E7%A7%91%E7%9B%AE%E7%94%B3%E5%91%8A/PID1_3_1.aspx"
    override val queryParameters: Map<String, Any>? = null
    override val headerFields: Map<String, String>? = mapOf(
        "Accept" to "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8",
        "Accept-Language" to "ja-jp"
    )
}