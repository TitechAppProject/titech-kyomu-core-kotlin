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
                name = "分光学",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.MONDAY, start = 11, end = 12, location = "S7-202(S000)"),
                    KyomuCoursePeriod(day = DayOfWeek.THURSDAY, start = 1, end = 2, location = "S7-202")
                ),
                year = 2022,
                quarters = listOf(1),
                code = "MAT.C302",
                ocwId = "202202171",
                teachers = listOf("矢野 哲司", "北沢 信章"),
                isForm8 = false
            ),
            resultJa[0]
        )

        assertFalse(resultJa.any { it.name == "固体物理学(格子系)" })

        assertEquals(
            KyomuCourse(
                name = "セラミックス実験第一",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.TUESDAY, start = 3, end = 4, location = "情報工学系計算機室， GSIC情報棟 3階307号室"),
                    KyomuCoursePeriod(day = DayOfWeek.FRIDAY, start = 3, end = 4, location = "情報工学系計算機室， GSIC情報棟 3階308号室")
                ),
                year = 2022,
                quarters = listOf(1),
                code = "MAT.C350",
                ocwId = "202202185",
                teachers = listOf("松下 伸広", "山口 晃"),
                isForm8 = false
            ),
            resultJa[1]
        )

        assertEquals(
            KyomuCourse(
                name = "結晶化学（C）",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.MONDAY, start = 7, end = 10, location = "西2号館教養科目物理学実験室（Introductory Physics Laboratory）"),
                    KyomuCoursePeriod(day = DayOfWeek.FRIDAY, start = 5, end = 6, location = "S7-202")
                ),
                year = 2022,
                quarters = listOf(1),
                code = "MAT.C301",
                ocwId = "202202170",
                teachers = listOf("鶴見 敬章", "保科 拓也"),
                isForm8 = false
            ),
            resultJa[2]
        )

        assertFalse(resultJa.any { it.name == "半導体物性" })

        assertEquals(
            KyomuCourse(
                name = "アルゴリズムとデータ構造",
                periods = emptyList(),
                year = 2022,
                quarters = listOf(2),
                code = "MCS.T213",
                ocwId = "202202382",
                teachers = listOf("森 立平"),
                isForm8 = false
            ),
            resultJa[5]
        )

        assertEquals(
            KyomuCourse(
                name = "確率微分方程式",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.TUESDAY, start = 3, end = 4, location = "W931"),
                    KyomuCoursePeriod(day = DayOfWeek.FRIDAY, start = 3, end = 4, location = "W931")
                ),
                year = 2022,
                quarters = listOf(4),
                code = "MCS.T419",
                ocwId = "202217437",
                teachers = listOf("中野 張", "三好 直人"),
                isForm8 = true
            ),
            resultJa[6]
        )

        assertEquals(
            KyomuCourse(
                name = "システム構築演習",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.TUESDAY, start = 3, end = 4, location = "情報工学系計算機室"),
                    KyomuCoursePeriod(day = DayOfWeek.FRIDAY, start = 3, end = 4, location = "情報工学系計算機室")
                ),
                year = 2022,
                quarters = listOf(4),
                code = "CSC.T375",
                ocwId = "202202449",
                teachers = listOf("小野 峻佑", "田村 康将"),
                isForm8 = false
            ),
            resultJa[7]
        )
    }

    @Test
    fun testParseReportCheckPageEn() {
        val titechKyomu = TitechKyomu()

        val htmlEn = TitechKyomuTest::class.java.getResource("/html/ReportCheckResultEnglish.html")!!.readText()

        val resultEn = titechKyomu.parseReportCheckPage(htmlEn)

        assertEquals(
            KyomuCourse(
                name = "Spectroscopy",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.MONDAY, start = 11, end = 12, location = "S7-202(S000)"),
                    KyomuCoursePeriod(day = DayOfWeek.THURSDAY, start = 1, end = 2, location = "S7-202")
                ),
                year = 2022,
                quarters = listOf(1),
                code = "MAT.C302",
                ocwId = "202202171",
                teachers = listOf("Yano Tetsuji", "Kitazawa Nobuaki"),
                isForm8 = false
            ),
            resultEn[0]
        )

        assertEquals(
            KyomuCourse(
                name = "Ceramics Laboratory I",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.MONDAY, start = 5, end = 8, location = "S7-204, 207, 209"),
                    KyomuCoursePeriod(day = DayOfWeek.THURSDAY, start = 5, end = 8, location = "S7-204, 207, 209")
                ),
                year = 2022,
                quarters = listOf(1),
                code = "MAT.C350",
                ocwId = "202202185",
                teachers = listOf("Matsushita Nobuhiro", "Yamaguchi Akira"),
                isForm8 = false
            ),
            resultEn[1]
        )

        assertFalse(resultEn.any { it.name == "Solid State Physics (Lattice)" })
        assertFalse(resultEn.any { it.name == "Semiconductor Physics" })

        assertEquals(
            KyomuCourse(
                name = "Introduction to Algorithms and Data Structures",
                periods = emptyList(),
                year = 2022,
                quarters = listOf(2),
                code = "MCS.T213",
                ocwId = "202202382",
                teachers = listOf("Mori Ryuhei"),
                isForm8 = false
            ),
            resultEn[5]
        )

        assertEquals(
            KyomuCourse(
                name = "Stochastic differential equations",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.TUESDAY, start = 3, end = 4, location = "W931"),
                    KyomuCoursePeriod(day = DayOfWeek.FRIDAY, start = 3, end = 4, location = "W931")
                ),
                year = 2022,
                quarters = listOf(4),
                code = "MCS.T419",
                ocwId = "202217437",
                teachers = listOf("Nakano Yumiharu", "Miyoshi Naoto"),
                isForm8 = true
            ),
            resultEn[6]
        )

        assertEquals(
            KyomuCourse(
                name = "Workshop on System Implementation",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.TUESDAY, start = 3, end = 4, location = "情報工学系計算機室"),
                    KyomuCoursePeriod(day = DayOfWeek.FRIDAY, start = 3, end = 4, location = "情報工学系計算機室")
                ),
                year = 2022,
                quarters = listOf(4),
                code = "CSC.T375",
                ocwId = "202202449",
                teachers = listOf("Ono Shunsuke", "Tamura Yasumasa"),
                isForm8 = false
            ),
            resultEn[7]
        )
    }
}