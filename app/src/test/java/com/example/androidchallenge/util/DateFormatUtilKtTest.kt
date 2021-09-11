package com.example.androidchallenge.util

import org.junit.Assert.assertEquals
import org.junit.Test
import java.time.ZoneId
import java.util.*

class DateFormatUtilKtTest {

    @Test
    fun `formatDate returns correct string`() {
        // Arrange
        val dateTime = 0L // Thursday 1/1/1970 UTC
        // Act
        val monthDayString = formatDate(dateTime, "UTC")
        // Assert
        assertEquals("1/1", monthDayString)
    }

    @Test
    fun `getWeekday returns correct string`() {
        // Arrange
        val dateTime = 0L // Thursday 1/1/1970 UTC

        // Act
        val dayString = getWeekday(dateTime, "UTC")

        // Assert
        assertEquals("Thu", dayString)
    }
}