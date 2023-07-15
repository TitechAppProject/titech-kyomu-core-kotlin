package app.titech.titechKyomuCore

import app.titech.titechKyomuCore.http.*
import app.titech.titechKyomuCore.`object`.DayOfWeek
import app.titech.titechKyomuCore.`object`.KyomuCourse
import app.titech.titechKyomuCore.`object`.KyomuCoursePeriod
import org.jsoup.Jsoup
import java.lang.Exception
import java.net.HttpCookie

class TitechKyomuLoginError : Exception()

class TitechKyomu(
    val httpClient: HTTPClient = HTTPClientImpl()
) {
    suspend fun loginTopPage(authCookies: List<HttpCookie>) {
        httpClient.setCookies(authCookies)
        val html = httpClient.send(TopPageRequest())
        if (!parseTopPage(html)) {
            throw TitechKyomuLoginError()
        }
    }

    suspend fun fetchKyomuCourseData(): List<KyomuCourse>
        = parseReportCheckPage(httpClient.send(ReportCheckPageRequest()))

    internal fun parseTopPage(html: String): Boolean {
        val title = Jsoup.parse(html).title()

        return title.contains("学生トップ") || title.contains("Top")
    }

    internal fun parseReportCheckPage(html: String): List<KyomuCourse> {
        val doc = Jsoup.parse(html)
        val title = doc.select("#ctl00_ContentPlaceHolder1_CheckResult1_ctl08_ctl13_lblTerm")
            .first()
            ?.html() ?: ""
        val year = "^(\\d+)".toRegex().find(title)?.value?.toIntOrNull() ?: 0

        return doc.select("#ctl00_ContentPlaceHolder1_CheckResult1_grid tr:not(:first-of-type)").mapNotNull { row ->
            val tds = row.select("td")

            val name = tds[6].select(".showAtPrintDiv").firstOrNull()?.html() ?: ""
            val resultContent = tds[9].html()
            if (!resultContent.contains("OK") && !resultContent.contains("○")) {
                return@mapNotNull null
            }

            val periodTd = tds[2]
            val periodContent = periodTd.html()

            val periodRegexpResult = "([日月火水木金土]|Sun|Mon|Tue|Wed|Thu|Fri|Sat)(\\d)-(\\d)\\s?(?:[(（]([^()（）]+)[)）])?".toRegex().findAll(periodContent)
            val periods = periodRegexpResult.map {
                KyomuCoursePeriod(
                    parseDayOfWeek(it.groupValues[1]),
                    it.groupValues[2].toIntOrNull() ?: -1,
                    it.groupValues[3].toIntOrNull() ?: -1,
                    it.groupValues[4]
                )
            }.toList()

            val quarters = parseQuarters(tds[1].html())
            val code = tds[5].html()
            val ocwId = tds[6].select("a")
                .firstOrNull()
                ?.attr("href")
                ?.run {
                    "JWC=([0-9]+)".toRegex().find(this)?.groupValues?.get(1)
                } ?: ""

            val isForm8Td = tds[12].html()
            val isForm8 = isForm8Td.contains("Form No.8") || isForm8Td.contains("様式第８号")

            KyomuCourse(
                name,
                periods,
                year,
                quarters,
                code,
                ocwId,
                isForm8
            )
        }
    }

    private fun parseQuarters(html: String): List<Int> {
        val str = html
            .replace("Q", "")
            .replace("[～〜~]".toRegex(), "-")

        return if (str.contains("-")) {
            val res = str.split("-").mapNotNull { it.toIntOrNull() }
            if (res.count() == 2)  (res[0]..res[1]).toList() else listOf()
        } else if (str.contains("・")) {
            str.split("・").mapNotNull { it.toIntOrNull() }
        } else {
            listOf(str).mapNotNull { it.toIntOrNull() }
        }
    }

    private fun parseDayOfWeek(str: String): DayOfWeek {
        return if (str.contains("日") || str.contains("Sun")) {
            DayOfWeek.SUNDAY
        } else if (str.contains("月") || str.contains("Mon")) {
            DayOfWeek.MONDAY
        } else if (str.contains("火") || str.contains("Tue")) {
            DayOfWeek.TUESDAY
        } else if (str.contains("水") || str.contains("Wed")) {
            DayOfWeek.WEDNESDAY
        } else if (str.contains("木") || str.contains("Thu")) {
            DayOfWeek.THURSDAY
        } else if (str.contains("金") || str.contains("Fri")) {
            DayOfWeek.FRIDAY
        } else if (str.contains("土") || str.contains("Sat")) {
            DayOfWeek.SATURDAY
        } else {
            DayOfWeek.UNKNOWN
        }
    }

    companion object {
        fun changeToMock() {
            BaseURL.changeToMock()
        }
    }
}