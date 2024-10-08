package com.jbjbjb.memoboard.config

import android.content.Context
import com.jbjbjb.memoboard.data.DBMemoRepository
import com.jbjbjb.memoboard.data.MemoDatabase
import com.jbjbjb.memoboard.data.MemoRepository

interface AppContainer {
    val memoRepository: MemoRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val memoRepository: MemoRepository by lazy {
        // LocalMemoRepository(LocalMemoProvider)
        DBMemoRepository(MemoDatabase.getDatabase(context).memoDao())
    }
}
