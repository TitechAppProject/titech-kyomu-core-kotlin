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
                name = "情報通信工学統合論基礎",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.MONDAY, start = 1, end = 2, location = "S4-201 (S421)"),
                    KyomuCoursePeriod(day = DayOfWeek.THURSDAY, start = 1, end = 2, location = "S4-201 (S421)")
                ),
                year = 2025,
                quarters = listOf(1),
                code = "ICT.A435",
                ocwId = "202536673",
                teachers = listOf("佐々木 広, ISLAM A K M MAHFUZUL, 一色 剛"),
                isValid = false,
                isForm8 = false
            ),
            resultJa[0]
        )

        assertEquals(
            KyomuCourse(
                name = "現代暗号理論",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.MONDAY, start = 3, end = 4, location = "WL2-301 (W631)"),
                    KyomuCoursePeriod(day = DayOfWeek.THURSDAY, start = 3, end = 4, location = "WL2-301 (W631)")
                ),
                year = 2025,
                quarters = listOf(1),
                code = "ICT.C401",
                ocwId = "202504669",
                teachers = listOf("尾形 わかは"),
                isValid = true,
                isForm8 = false
            ),
            resultJa[1]
        )

        assertEquals(
            KyomuCourse(
                name = "分散アルゴリズム",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.MONDAY, start = 7, end = 8, location = "M-356(H132)"),
                    KyomuCoursePeriod(day = DayOfWeek.THURSDAY, start = 7, end = 8, location = "M-356(H132)")
                ),
                year = 2025,
                quarters = listOf(1),
                code = "CSC.T438",
                ocwId = "202510377",
                teachers = listOf("DEFAGO XAVIER"),
                isValid = true,
                isForm8 = false
            ),
            resultJa[2]
        )

        assertEquals(
            KyomuCourse(
                name = "音声情報工学",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.TUESDAY, start = 1, end = 2, location = "G1-103 (G114)"),
                    KyomuCoursePeriod(day = DayOfWeek.FRIDAY, start = 1, end = 2, location = "G1-103 (G114)")
                ),
                year = 2025,
                quarters = listOf(1),
                code = "ICT.H503",
                ocwId = "202504723",
                teachers = listOf("篠﨑 隆宏"),
                isValid = true,
                isForm8 = false
            ),
            resultJa[3]
        )

        assertEquals(
            KyomuCourse(
                name = "仮想世界システム",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.TUESDAY, start = 3, end = 4, location = "J2-203 (J221)"),
                    KyomuCoursePeriod(day = DayOfWeek.FRIDAY, start = 3, end = 4, location = "J2-203 (J221)")
                ),
                year = 2025,
                quarters = listOf(1),
                code = "ICT.H507",
                ocwId = "202504726",
                teachers = listOf("長谷川 晶一"),
                isValid = true,
                isForm8 = false
            ),
            resultJa[4]
        )

        assertEquals(
            KyomuCourse(
                name = "人間情報システム概論I",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.MONDAY, start = 1, end = 2, location = "G5-105 (G511)"),
                    KyomuCoursePeriod(day = DayOfWeek.THURSDAY, start = 1, end = 2, location = "G5-105 (G511)")
                ),
                year = 2025,
                quarters = listOf(2),
                code = "ICT.A406",
                ocwId = "202504680",
                teachers = listOf("船越 孝太郎, 小池 康晴, 山口 雅浩"),
                isValid = true,
                isForm8 = false
            ),
            resultJa[5]
        )

        assertEquals(
            KyomuCourse(
                name = "先端技術を用いた社会課題解決",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.MONDAY, start = 3, end = 4, location = "G1-103 (G114)")
                ),
                year = 2025,
                quarters = listOf(2),
                code = "ICT.D401.L (ESD.E407)",
                ocwId = "202534821",
                teachers = listOf("中谷 桃子, 山口 雅浩, ※近藤 隆"),
                isValid = true,
                isForm8 = false
            ),
            resultJa[6]
        )

        assertEquals(
            KyomuCourse(
                name = "無線信号処理",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.TUESDAY, start = 3, end = 4, location = "S3-215 (S321)"),
                    KyomuCoursePeriod(day = DayOfWeek.FRIDAY, start = 3, end = 4, location = "S3-215 (S321)")
                ),
                year = 2025,
                quarters = listOf(2),
                code = "ICT.S407",
                ocwId = "202504673",
                teachers = listOf("府川 和彦"),
                isValid = true,
                isForm8 = false
            ),
            resultJa[7]
        )

        assertEquals(
            KyomuCourse(
                name = "文系エッセンス２０：西洋思想 1",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.WEDNESDAY, start = 3, end = 4, location = "G2-201(G223)")
                ),
                year = 2025,
                quarters = listOf(2),
                code = "LAH.S420-01",
                ocwId = "202508468",
                teachers = listOf("BEKTAS YAKUP"),
                isValid = true,
                isForm8 = false
            ),
            resultJa[8]
        )

        assertEquals(
            KyomuCourse(
                name = "修士キャリア構築基礎 B",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.WEDNESDAY, start = 5, end = 6, location = "")
                ),
                year = 2025,
                quarters = listOf(2),
                code = "ENT.C401-04",
                ocwId = "202536241",
                teachers = listOf("若山 浩二, 和泉 章, 伊東 幸子"),
                isValid = true,
                isForm8 = false
            ),
            resultJa[9]
        )

        assertEquals(
            KyomuCourse(
                name = "TOEFL対策セミナー第十四 1",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.WEDNESDAY, start = 7, end = 8, location = "M-155(H1104)")
                ),
                year = 2025,
                quarters = listOf(2),
                code = "LAE.E452-01",
                ocwId = "202503675",
                teachers = listOf("DE FERRANTI HUGH BARRY ZIANI"),
                isValid = true,
                isForm8 = false
            ),
            resultJa[10]
        )

        assertEquals(
            KyomuCourse(
                name = "情報通信工学講究S1",
                periods = listOf(),
                year = 2025,
                quarters = listOf(1, 2),
                code = "ICT.Z491",
                ocwId = "202504683",
                teachers = listOf("指導教員"),
                isValid = true,
                isForm8 = false
            ),
            resultJa[11]
        )
    }

    @Test
    fun testParseReportCheckPageEn() {
        val titechKyomu = TitechKyomu()

        val htmlEn = TitechKyomuTest::class.java.getResource("/html/ReportCheckResultEnglish.html")!!.readText()

        val resultEn = titechKyomu.parseReportCheckPage(htmlEn)

        assertEquals(
            KyomuCourse(
                name = "Communications and Computer Engineering - Fundamentals",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.MONDAY, start = 1, end = 2, location = "S4-201 (S421)"),
                    KyomuCoursePeriod(day = DayOfWeek.THURSDAY, start = 1, end = 2, location = "S4-201 (S421)")
                ),
                year = 2025,
                quarters = listOf(1),
                code = "ICT.A435",
                ocwId = "202536673",
                teachers = listOf("Sasaki Hiroshi, Islam A K M Mahfuzul, Isshiki Tsuyoshi"),
                isValid = false,
                isForm8 = false
            ),
            resultEn[0]
        )

        assertEquals(
            KyomuCourse(
                name = "Modern Cryptography",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.MONDAY, start = 3, end = 4, location = "WL2-301 (W631)"),
                    KyomuCoursePeriod(day = DayOfWeek.THURSDAY, start = 3, end = 4, location = "WL2-301 (W631)")
                ),
                year = 2025,
                quarters = listOf(1),
                code = "ICT.C401",
                ocwId = "202504669",
                teachers = listOf("Ogata Wakaha"),
                isValid = true,
                isForm8 = false
            ),
            resultEn[1]
        )

        assertEquals(
            KyomuCourse(
                name = "Distributed Algorithms",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.MONDAY, start = 7, end = 8, location = "M-356(H132)"),
                    KyomuCoursePeriod(day = DayOfWeek.THURSDAY, start = 7, end = 8, location = "M-356(H132)")
                ),
                year = 2025,
                quarters = listOf(1),
                code = "CSC.T438",
                ocwId = "202510377",
                teachers = listOf("Defago Xavier"),
                isValid = true,
                isForm8 = false
            ),
            resultEn[2]
        )

        assertEquals(
            KyomuCourse(
                name = "Speech Information Technology",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.TUESDAY, start = 1, end = 2, location = "G1-103 (G114)"),
                    KyomuCoursePeriod(day = DayOfWeek.FRIDAY, start = 1, end = 2, location = "G1-103 (G114)")
                ),
                year = 2025,
                quarters = listOf(1),
                code = "ICT.H503",
                ocwId = "202504723",
                teachers = listOf("Shinozaki Takahiro"),
                isValid = true,
                isForm8 = false
            ),
            resultEn[3]
        )

        assertEquals(
            KyomuCourse(
                name = "Virtual Reality and Interaction",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.TUESDAY, start = 3, end = 4, location = "J2-203 (J221)"),
                    KyomuCoursePeriod(day = DayOfWeek.FRIDAY, start = 3, end = 4, location = "J2-203 (J221)")
                ),
                year = 2025,
                quarters = listOf(1),
                code = "ICT.H507",
                ocwId = "202504726",
                teachers = listOf("Hasegawa Shoichi"),
                isValid = true,
                isForm8 = false
            ),
            resultEn[4]
        )

        assertEquals(
            KyomuCourse(
                name = "Human-Centric Information Systems I",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.MONDAY, start = 1, end = 2, location = "G5-105 (G511)"),
                    KyomuCoursePeriod(day = DayOfWeek.THURSDAY, start = 1, end = 2, location = "G5-105 (G511)")
                ),
                year = 2025,
                quarters = listOf(2),
                code = "ICT.A406",
                ocwId = "202504680",
                teachers = listOf("Funakoshi Kotaro, Koike Yasuharu, Yamaguchi Masahiro"),
                isValid = true,
                isForm8 = false
            ),
            resultEn[5]
        )

        assertEquals(
            KyomuCourse(
                name = "Solving Social Issues with Cutting-Edge Technology",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.MONDAY, start = 3, end = 4, location = "G1-103 (G114)")
                ),
                year = 2025,
                quarters = listOf(2),
                code = "ICT.D401.L (ESD.E407)",
                ocwId = "202534821",
                teachers = listOf("Nakatani Momoko, Yamaguchi Masahiro, ※Kondo Takashi"),
                isValid = true,
                isForm8 = false
            ),
            resultEn[6]
        )

        assertEquals(
            KyomuCourse(
                name = "Wireless Signal Processing",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.TUESDAY, start = 3, end = 4, location = "S3-215 (S321)"),
                    KyomuCoursePeriod(day = DayOfWeek.FRIDAY, start = 3, end = 4, location = "S3-215 (S321)")
                ),
                year = 2025,
                quarters = listOf(2),
                code = "ICT.S407",
                ocwId = "202504673",
                teachers = listOf("Fukawa Kazuhiko"),
                isValid = true,
                isForm8 = false
            ),
            resultEn[7]
        )

        assertEquals(
            KyomuCourse(
                name = "Essence of Humanities and Social Sciences20:Western Thought 1",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.WEDNESDAY, start = 3, end = 4, location = "G2-201(G223)")
                ),
                year = 2025,
                quarters = listOf(2),
                code = "LAH.S420-01",
                ocwId = "202508468",
                teachers = listOf("Bektas Yakup"),
                isValid = true,
                isForm8 = false
            ),
            resultEn[8]
        )

        assertEquals(
            KyomuCourse(
                name = "Master's Career Development Basics B",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.WEDNESDAY, start = 5, end = 6, location = "")
                ),
                year = 2025,
                quarters = listOf(2),
                code = "ENT.C401-04",
                ocwId = "202536241",
                teachers = listOf("Wakayama Koji, Izumi Akira, Ito Sachiko"),
                isValid = true,
                isForm8 = false
            ),
            resultEn[9]
        )

        assertEquals(
            KyomuCourse(
                name = "TOEFL Seminar 14 1",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.WEDNESDAY, start = 7, end = 8, location = "M-155(H1104)")
                ),
                year = 2025,
                quarters = listOf(2),
                code = "LAE.E452-01",
                ocwId = "202503675",
                teachers = listOf("De Ferranti Hugh Barry Ziani"),
                isValid = true,
                isForm8 = false
            ),
            resultEn[10]
        )

        assertEquals(
            KyomuCourse(
                name = "Seminar in Information and Communications Engineering S1",
                periods = listOf(),
                year = 2025,
                quarters = listOf(1, 2),
                code = "ICT.Z491",
                ocwId = "202504683",
                teachers = listOf("Academic Supervisor"),
                isValid = true,
                isForm8 = false
            ),
            resultEn[11]
        )
    }

    @Test
    fun testParseTemporaryReportCheckPageJa() {
        val titechKyomu = TitechKyomu()

        val htmlJa = TitechKyomuTest::class.java.getResource("/html/ReportCheckResultTemporarySaveJapanese.html")!!.readText()

        val resultJa = titechKyomu.parseReportCheckPage(htmlJa)

        assertEquals(
            KyomuCourse(
                name = "情報通信工学統合論基礎",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.MONDAY, start = 1, end = 2, location = "S4-201 (S421)"),
                    KyomuCoursePeriod(day = DayOfWeek.THURSDAY, start = 1, end = 2, location = "S4-201 (S421)")
                ),
                year = 2024,
                quarters = listOf(1),
                code = "ICT.A435",
                ocwId = "202536673",
                teachers = listOf("佐々木 広, ISLAM A K M MAHFUZUL, 一色 剛"),
                isValid = true,
                isForm8 = false
            ),
            resultJa[0]
        )
    }

    @Test
    fun testParseTemporaryReportCheckPageEn() {
        val titechKyomu = TitechKyomu()

        val htmlEn = TitechKyomuTest::class.java.getResource("/html/ReportCheckResultTemporarySaveEnglish.html")!!.readText()

        val resultEn = titechKyomu.parseReportCheckPage(htmlEn)


        assertEquals(
            KyomuCourse(
                name = "Communications and Computer Engineering - Fundamentals",
                periods = listOf(
                    KyomuCoursePeriod(day = DayOfWeek.MONDAY, start = 1, end = 2, location = "S4-201 (S421)"),
                    KyomuCoursePeriod(day = DayOfWeek.THURSDAY, start = 1, end = 2, location = "S4-201 (S421)")
                ),
                year = 2024,
                quarters = listOf(1),
                code = "ICT.A435",
                ocwId = "202536673",
                teachers = listOf("Sasaki Hiroshi, Islam A K M Mahfuzul, Isshiki Tsuyoshi"),
                isValid = true,
                isForm8 = false
            ),
            resultEn[0]
        )

    }
}