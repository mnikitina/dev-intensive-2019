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

@RequiresApi(Build.VERSION_CODES.O)
fun Date.humanizeDiff(date: Date = Date()): String {
    val mStart: LocalDateTime = convertTolocalDateTime(this)
    val mEnd: LocalDateTime = convertTolocalDateTime(Date())

    val years = ChronoUnit.YEARS.between(mStart, mEnd)
    val months = ChronoUnit.MONTHS.between(mStart, mEnd)
    val days = ChronoUnit.DAYS.between(mStart, mEnd)
    val hours = ChronoUnit.HOURS.between(mStart, mEnd)
    val minutes = ChronoUnit.MINUTES.between(mStart, mEnd)
    val seconds = ChronoUnit.SECONDS.between(mStart, mEnd)

    if (hours > 1) {
        return (if (years != 0L) {
            "$years  Лет назад"
        } else "") +
                (if (months != 0L) {
                    "$months Месяцев назад"
                } else {
                    (if (days != 0L) {
                        "$days Дней назад"
                    } else {
                        if (hours != 0L) {
                            "$hours  Часов назад"
                        } else {}
                    })
                })
    } else {
        return if (seconds != 0L) {
            "$seconds Секунд назад"
        } else "$minutes Минут назад"
    }
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