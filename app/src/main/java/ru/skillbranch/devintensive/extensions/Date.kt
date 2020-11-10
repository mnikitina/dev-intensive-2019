package ru.skillbranch.devintensive.extensions

import android.os.Build
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.time.temporal.ChronoUnit
import java.util.*


const val SECOND = 1000L
const val MINUTE = 60 * SECOND
const val HOUR = 60 * MINUTE
const val DAY = 24 * HOUR

fun Date.format(pattern: String = "HH:mm:ss dd.MM.yy"): String {
    val dateFormat = SimpleDateFormat(pattern, Locale("ru"))
    return dateFormat.format(this)
}

fun Date.add(value: Int, units: TimeUnits = TimeUnits.SECOND): Date {
    var time = this.time
    time += when (units) {
        TimeUnits.SECOND -> value * SECOND
        TimeUnits.MINUTE -> value * MINUTE
        TimeUnits.HOUR -> value * HOUR
        TimeUnits.DAY -> value * DAY
    }
    this.time = time
    return this
}

//@RequiresApi(Build.VERSION_CODES.O)
fun Date.humanizeDiff(date: Date = Date()): String {
/*
    val mStart: LocalDateTime = convertTolocalDateTime(this)
    val mEnd: LocalDateTime = convertTolocalDateTime(Date())

    //val years = ChronoUnit.YEARS.between(mStart, mEnd)
    //val months = ChronoUnit.MONTHS.between(mStart, mEnd)
    val days = ChronoUnit.DAYS.between(mStart, mEnd)
    val hours = ChronoUnit.HOURS.between(mStart, mEnd)
    val minutes = ChronoUnit.MINUTES.between(mStart, mEnd)
    val seconds = ChronoUnit.SECONDS.between(mStart, mEnd)

    return if (days > 360) return "более года назад"
    else if (hours in 26..8640) return "$days дней назад"
    else if (hours in 22..26) return "день назад"
    else if (minutes in 75..1320) return "$hours часов назад"
    else if (minutes in 45..75) return "час назад"
    else if (seconds in 45..2700) return "$minutes минут назад"
    else if (seconds in 45..75) return "минуту назад"
    else if (seconds in 1..45) return "несколько секунд назад"
    else if (seconds in 0..1) return "только что" else ""
*/
    return ""
}

@RequiresApi(Build.VERSION_CODES.O)
private fun convertTolocalDateTime(date: Date = Date()): LocalDateTime {
    val calendarDate = Calendar.getInstance()
    calendarDate.time = date
    val resultDate: LocalDateTime = LocalDateTime.of(
        calendarDate[Calendar.YEAR],
        calendarDate[Calendar.MONTH] + 1,
        calendarDate[Calendar.DAY_OF_MONTH],
        calendarDate[Calendar.HOUR_OF_DAY],
        calendarDate[Calendar.MINUTE],
        calendarDate[Calendar.SECOND]
    )
    return resultDate
}

enum class TimeUnits {
    SECOND,
    MINUTE,
    HOUR,
    DAY
}