package app.titech.titechKyomuCore

import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Test
import java.net.HttpCookie
import kotlin.test.assertEquals

class TitechKyomuOnProductionTest {
    @Test
    fun testLogin() {
        TitechKyomu.changeToMock()

        val titechkyomu = TitechKyomu()
        runBlocking {
            titechkyomu.loginTopPage(
                HttpCookie(
                    "AUTH_SESSION_ID",
                    "value"
                )
            )
        }
    }

    @Test
    fun testFetchKyomuCourseData() {
        TitechKyomu.changeToMock()

        val titechkyomu = TitechKyomu()
        runBlocking {
            titechkyomu.loginTopPage(
                HttpCookie(
                    "AUTH_SESSION_ID",
                    "value"
                )
            )
            val courses = titechkyomu.fetchKyomuCourseData()

            assertEquals(
                8,
                courses.count()
            )
        }
    }
}