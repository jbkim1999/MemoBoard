package com.example.memoboard.config

import android.content.Context
import com.example.memoboard.data.LocalSectionProvider
import com.example.memoboard.data.LocalSectionRepository
import com.example.memoboard.data.MemoRepository
import com.example.memoboard.data.SectionRepository

interface AppContainer {
    val memoRepository: MemoRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val memoRepository: memoRepository by lazy {
        // DBSectionRepository(MemoDatabase.getDatabase(context).sectionDao())
        LocalSectionRepository(LocalSectionProvider)
    }
}
