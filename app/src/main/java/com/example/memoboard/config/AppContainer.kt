package com.example.memoboard.config

import android.content.Context
import com.example.memoboard.data.DBMemoRepository
import com.example.memoboard.data.LocalMemoProvider
import com.example.memoboard.data.LocalMemoRepository
import com.example.memoboard.data.MemoDatabase
import com.example.memoboard.data.MemoRepository

interface AppContainer {
    val memoRepository: MemoRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val memoRepository: MemoRepository by lazy {
        DBMemoRepository(MemoDatabase.getDatabase(context).memoDao())
        LocalMemoRepository(LocalMemoProvider)
    }
}
