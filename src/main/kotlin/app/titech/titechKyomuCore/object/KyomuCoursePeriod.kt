package app.titech.titechKyomuCore.`object`

data class KyomuCoursePeriod(
    val day: DayOfWeek,
    val start: Int,
    val end: Int,
    val location: String
)
