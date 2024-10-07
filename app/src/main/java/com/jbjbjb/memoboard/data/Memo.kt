package com.jbjbjb.memoboard.data

import androidx.room.Entity
import androidx.room.PrimaryKey

import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Locale

@Entity(tableName = "memos")
data class Memo(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,
    var name: String = "",
    var content: String = "",
    var lastModifiedDate: LocalDateTime = LocalDateTime.now()
) {
    val lastModifiedFormattedDate: String
        get() {
            val formatter = DateTimeFormatter.ofPattern(
                "hh:mma, MMMM d, yyyy",
                Locale.ENGLISH
            )

            return "last modified: ${lastModifiedDate.format(formatter)}".lowercase()
        }
}
