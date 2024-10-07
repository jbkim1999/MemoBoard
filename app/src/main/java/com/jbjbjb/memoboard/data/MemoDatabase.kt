package com.jbjbjb.memoboard.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.jbjbjb.memoboard.config.DateConverter

@Database(entities = [Memo::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class MemoDatabase : RoomDatabase() {

    abstract fun memoDao(): MemoDao

    companion object {
        @Volatile
        private var Instance: MemoDatabase? = null

        fun getDatabase(context: Context): MemoDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, MemoDatabase::class.java, "memo_database")
                    .fallbackToDestructiveMigration()
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
