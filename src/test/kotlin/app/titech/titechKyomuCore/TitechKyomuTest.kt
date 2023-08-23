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
        val titechkyomu = TitechKyomu()

        val html = TitechKyomuTest::class.java.getResource("/html/ReportCheckResultJapanese.html")!!.readText()

        val courses = titechkyomu.parseReportCheckPage(html)

        assertEquals(
            KyomuCourse(
                "分光学",
                listOf(
                    KyomuCoursePeriod(DayOfWeek.MONDAY, 1, 2, "S7-202(S777)"),
                    KyomuCoursePeriod(DayOfWeek.THURSDAY, 1, 2, "S7-202(S777)")
                ),
                2022,
                listOf(1),
                "MAT.C302",
                "202202171",
                false
            ),
            courses[0]
        )

        assertFalse(courses.any { it.name == "固体物理学(格子系)"} )

        assertEquals(
            KyomuCourse(
                "確率微分方程式",
                listOf(
                    KyomuCoursePeriod(DayOfWeek.TUESDAY, 3, 4, "W931"),
                    KyomuCoursePeriod(DayOfWeek.FRIDAY, 3, 4, "W931")
                ),
                2022,
                listOf(4),
                "MCS.T419",
                "202217437",
                true
            ),
            courses[7]
        )
        assertEquals(
            KyomuCourse(
                "システム構築演習",
                listOf(
                    KyomuCoursePeriod(DayOfWeek.TUESDAY, 3, 4, "情報工学系計算機室"),
                    KyomuCoursePeriod(DayOfWeek.FRIDAY, 3, 4, "情報工学系計算機室")
                ),
                2022,
                listOf(4),
                "CSC.T375",
                "202202449",
                false
            ),
            courses[8]
        )

        assertEquals(
            KyomuCourse(
                "数理・計算科学講究S2",
                listOf(),
            2022,
                listOf(1, 2),
            "MCS.Z591",
            "202304050",
            false
            ),
            courses[9]
        )
    }

    @Test
    fun testParseReportCheckPageEn() {
        val titechkyomu = TitechKyomu()

        val html = TitechKyomuTest::class.java.getResource("/html/ReportCheckResultEnglish.html")!!.readText()

        val courses = titechkyomu.parseReportCheckPage(html)

        assertEquals(
            KyomuCourse(
                "Spectroscopy",
                listOf(
                    KyomuCoursePeriod(DayOfWeek.MONDAY, 1, 2, "S7-202(S777)"),
                    KyomuCoursePeriod(DayOfWeek.THURSDAY, 1, 2, "S7-202(S777)")
                ),
                2022,
                listOf(1),
                "MAT.C302",
                "202202171",
                false
            ),
            courses[0]
        )

        assertFalse(courses.any { it.name == "Solid State Physics (Lattice)"} )

        assertEquals(
            KyomuCourse(
                "Stochastic differential equations",
                listOf(
                    KyomuCoursePeriod(DayOfWeek.TUESDAY, 3, 4, "W931"),
                    KyomuCoursePeriod(DayOfWeek.FRIDAY, 3, 4, "W931")
                ),
                2022,
                listOf(4),
                "MCS.T419",
                "202217437",
                true
            ),
            courses[7]
        )
        assertEquals(
            KyomuCourse(
                "Workshop on System Implementation",
                listOf(
                    KyomuCoursePeriod(DayOfWeek.TUESDAY, 3, 4, "情報工学系計算機室"),
                    KyomuCoursePeriod(DayOfWeek.FRIDAY, 3, 4, "情報工学系計算機室")
                ),
                2022,
                listOf(4),
                "CSC.T375",
                "202202449",
                false
            ),
            courses[8]
        )

        assertEquals(
            KyomuCourse(
                "Seminar on Mathematical and Computing Science S2",
                listOf(),
                2022,
                listOf(1, 2),
                "MCS.Z591",
                "202304050",
                false
            ),
            courses[9]
        )
    }
}