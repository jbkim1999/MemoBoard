package com.example.memoboard.config

import androidx.room.TypeConverter
import java.time.LocalDateTime

class DateConverter {
    @TypeConverter
    fun toDate(dateString: String): LocalDateTime {
        return LocalDateTime.parse(dateString)
    }

    @TypeConverter
    fun toString(date: LocalDateTime): String {
        return date.toString()
    }
}
