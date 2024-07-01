package com.example.memoboard.data

import androidx.room.Entity
import androidx.room.PrimaryKey

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Entity(tableName = "sections")
data class Section(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String = "",
    var content: String = "",
    var lastModifiedDate: LocalDateTime
) {
    val lastModifiedFormattedDate : String
        get() {
            val formatter = DateTimeFormatter.ofPattern(
                "hh:mma, MMMM d, yyyy",
                Locale.ENGLISH
            )

            return "last modified: ${lastModifiedDate.format(formatter)}".lowercase()
        }
}
