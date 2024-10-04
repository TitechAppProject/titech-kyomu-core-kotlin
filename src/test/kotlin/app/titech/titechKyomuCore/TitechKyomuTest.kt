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

    @Test
    fun testParseTemporaryReportCheckPageJa() {
        val titechKyomu = TitechKyomu()

        val htmlJa = TitechKyomuTest::class.java.getResource("/html/ReportCheckResultTemporarySaveJapanese.html")!!.readText()

        val resultJa = titechKyomu.parseReportCheckPage(htmlJa)

        assertEquals(
            KyomuCourse(
                name = "学士特定課題研究（情報通信系）",
                periods = emptyList(),
                year = 2024,
                quarters = listOf(3, 4),
                code = "ICT.Z389-02",
                ocwId = "202411248",
                teachers = listOf("各 教員"),
                isForm8 = false
            ),
            resultJa[0]
        )

        assertEquals(
            KyomuCourse(
                name = "情報通信工学統合論発展",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.MONDAY, start = 1, end = 2, location = "M-123(H111)"),
                    KyomuCoursePeriod(day = DayOfWeek.THURSDAY, start = 1, end = 2, location = "M-123(H111)")
                ),
                year = 2024,
                quarters = listOf(3),
                code = "ICT.A436",
                ocwId = "202436674",
                teachers = listOf("松本 隆太郎, 山田 功, 宮田 純子"),
                isForm8 = false
            ),
            resultJa[1]
        )

        assertEquals(
            KyomuCourse(
                name = "人間情報システム概論II",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.MONDAY, start = 1, end = 2, location = "G3-202(G311)"),
                    KyomuCoursePeriod(day = DayOfWeek.THURSDAY, start = 1, end = 2, location = "G3-202(G311)"),
                    ),
                year = 2024,
                quarters = listOf(4),
                code = "ICT.A418",
                ocwId = "202404681",
                teachers = listOf("永井 岳大, 山口 雅浩, 奥村 学"),
                isForm8 = false
            ),
            resultJa[2]
        )
    }

    @Test
    fun testParseTemporaryReportCheckPageEn() {
        val titechKyomu = TitechKyomu()

        val htmlEn = TitechKyomuTest::class.java.getResource("/html/ReportCheckResultTemporarySaveEnglish.html")!!.readText()

        val resultEn = titechKyomu.parseReportCheckPage(htmlEn)

        assertEquals(
            KyomuCourse(
                name = "Independent research project(ICT)",
                periods = emptyList(),
                year = 2024,
                quarters = listOf(3, 4),
                code = "ICT.Z389-02",
                ocwId = "202411248",
                teachers = listOf("Teaching Staffs"),
                isForm8 = false
            ),
            resultEn[0]
        )

        assertEquals(
            KyomuCourse(
                name = "Communications and Computer Engineering - Advanced Concepts",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.MONDAY, start = 1, end = 2, location = "M-123(H111)"),
                    KyomuCoursePeriod(day = DayOfWeek.THURSDAY, start = 1, end = 2, location = "M-123(H111)")
                ),
                year = 2024,
                quarters = listOf(3),
                code = "ICT.A436",
                ocwId = "202436674",
                teachers = listOf("Matsumoto Ryutaroh, Yamada Isao, Miyata Sumiko"),
                isForm8 = false
            ),
            resultEn[1]
        )

        assertEquals(
            KyomuCourse(
                name = "Human-Centric Information Systems II",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.MONDAY, start = 1, end = 2, location = "G3-202(G311)"),
                    KyomuCoursePeriod(day = DayOfWeek.THURSDAY, start = 1, end = 2, location = "G3-202(G311)"),
                ),
                year = 2024,
                quarters = listOf(4),
                code = "ICT.A418",
                ocwId = "202404681",
                teachers = listOf("Nagai Takehiro, Yamaguchi Masahiro, Okumura Manabu"),
                isForm8 = false
            ),
            resultEn[2]
        )
    }
}