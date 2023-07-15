package app.titech.titechKyomuCore.`object`

data class KyomuCourse(
    val name: String,
    val periods: List<KyomuCoursePeriod>,
    val year: Int,
    val quarters: List<Int>,
    val code: String,
    val ocwId: String,
    val isForm8: Boolean
)
