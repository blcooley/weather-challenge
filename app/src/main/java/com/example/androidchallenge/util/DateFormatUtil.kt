package com.example.androidchallenge.util

import java.util.*

fun formatDate(dateTime: Long, timeZoneString: String? = null): String {
    val locale = Locale.getDefault()
    val calendar = Calendar.getInstance(locale)
    timeZoneString?.let { calendar.timeZone = TimeZone.getTimeZone(it) }
    calendar.timeInMillis = dateTime * 1000
    val month = calendar.get(Calendar.MONTH) + 1
    val dayOfMonth = calendar.get(Calendar.DAY_OF_MONTH)
    return "$month/$dayOfMonth"
}

fun getWeekday(dateTime: Long, timeZoneString: String? = null): String {
    val locale = Locale.getDefault()
    val calendar = Calendar.getInstance(locale)
    timeZoneString?.let { calendar.timeZone = TimeZone.getTimeZone(it) }
    calendar.timeInMillis = dateTime * 1000
    return calendar.getDisplayName(Calendar.DAY_OF_WEEK, Calendar.SHORT, locale) ?: ""
}
