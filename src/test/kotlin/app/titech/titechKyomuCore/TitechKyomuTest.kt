package app.titech.titechKyomuCore

import app.titech.titechKyomuCore.`object`.DayOfWeek
import app.titech.titechKyomuCore.`object`.KyomuCourse
import app.titech.titechKyomuCore.`object`.KyomuCoursePeriod
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class TitechKyomuTest {
    @Test
    fun testParseTopPageJa() {
        val titechkyomu = TitechKyomu()

        val html = TitechKyomuTest::class.java.getResource("/html/TopJapanese.html")!!.readText()

        assertTrue { titechkyomu.parseTopPage(html) }
    }

    @Test
    fun testParseTopPageEn() {
        val titechkyomu = TitechKyomu()

        val html = TitechKyomuTest::class.java.getResource("/html/TopEnglish.html")!!.readText()

        assertTrue { titechkyomu.parseTopPage(html) }
    }

    @Test
    fun testParseTopPageMaintenance() {
        val titechkyomu = TitechKyomu()

        val html = TitechKyomuTest::class.java.getResource("/html/TopMaintenance.html")!!.readText()

        assertFalse { titechkyomu.parseTopPage(html) }
    }

    @Test
    fun testParseReportCheckPageJa() {
        val titechKyomu = TitechKyomu()

        val htmlJa = TitechKyomuTest::class.java.getResource("/html/ReportCheckResultJapanese.html")!!.readText()

        val resultJa = titechKyomu.parseReportCheckPage(htmlJa)

        assertEquals(
            KyomuCourse(
                name = "世界を知る：南・東南アジア",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.WEDNESDAY, start = 3, end = 4, location = "")
                ),
                year = 2024,
                quarters = listOf(3),
                code = "LAH.A505",
                ocwId = "202403782",
                teachers = listOf("※田中 李歩"),
                isForm8 = false
            ),
            resultJa[0]
        )

        assertEquals(
            KyomuCourse(
                name = "半導体物性特論（材料）",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.TUESDAY, start = 1, end = 2, location = "J2-303(J234)"),
                    KyomuCoursePeriod(day = DayOfWeek.FRIDAY, start = 1, end = 2, location = "J2-303(J234)")
                ),
                year = 2024,
                quarters = listOf(3),
                code = "ESI.J442.L (MAT.C404)",
                ocwId = "202402959",
                teachers = listOf("真島 豊, 平松 秀典"),
                isForm8 = true // Check Form8
            ),
            resultJa[1]
        )

        // 特許の科目をNGに
        assertFalse(resultJa.any { it.name == "知的情報資源の活用と特許" })

        assertEquals(
            KyomuCourse(
                name = "エネルギーイノベーション協創プロジェクト",
                periods = emptyList(),
                year = 2024,
                quarters = listOf(3, 4),
                code = "ESI.B502",
                ocwId = "202404150",
                teachers = listOf("コース主任, 渡部 卓雄, 難波江 裕太"),
                isForm8 = false
            ),
            resultJa[5]
        )
    }

    @Test
    fun testParseReportCheckPageEn() {
        val titechKyomu = TitechKyomu()

        val htmlEn = TitechKyomuTest::class.java.getResource("/html/ReportCheckResultEnglish.html")!!.readText()

        val resultEn = titechKyomu.parseReportCheckPage(htmlEn)

        assertEquals(
            KyomuCourse(
                name = "Area Studies: South and Southeast Asia",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.WEDNESDAY, start = 3, end = 4, location = "")
                ),
                year = 2024,
                quarters = listOf(3),
                code = "LAH.A505",
                ocwId = "202403782",
                teachers = listOf("※Tanaka Riho"),
                isForm8 = false
            ),
            resultEn[0]
        )

        assertEquals(
            KyomuCourse(
                name = "Physics and Chemistry of Semiconductors",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.TUESDAY, start = 1, end = 2, location = "J2-303(J234)"),
                    KyomuCoursePeriod(day = DayOfWeek.FRIDAY, start = 1, end = 2, location = "J2-303(J234)")
                ),
                year = 2024,
                quarters = listOf(3),
                code = "ESI.J442.L (MAT.C404)",
                ocwId = "202402959",
                teachers = listOf("Majima Yutaka, Hiramatsu Hidenori"),
                isForm8 = true // Check Form8
            ),
            resultEn[1]
        )

        // 特許の科目をNGに
        assertFalse(resultEn.any { it.name == "Utilization of Intelligent Information Resources and Patents" })

        assertEquals(
            KyomuCourse(
                name = "Energy innovation co-creative project",
                periods = emptyList(),
                year = 2024,
                quarters = listOf(3, 4),
                code = "ESI.B502",
                ocwId = "202404150",
                teachers = listOf("Head, Watanabe Takuo, Nabae Yuta"),
                isForm8 = false
            ),
            resultEn[5]
        )
    }
}