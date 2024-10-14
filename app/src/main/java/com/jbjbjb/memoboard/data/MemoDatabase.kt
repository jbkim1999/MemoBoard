package com.jbjbjb.memoboard.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import com.jbjbjb.memoboard.config.DateConverter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
                    .addCallback(object : Callback() {
                        override fun onCreate(db: SupportSQLiteDatabase) {
                            super.onCreate(db)
                            Instance?.let { database ->
                                CoroutineScope(Dispatchers.IO).launch {
                                    database.memoDao().insertMemo(
                                        LocalMemoProvider.getIntroMemo()
                                    )
                                }
                            }
                        }
                    })
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
