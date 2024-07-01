package com.example.memoboard.config

import android.content.Context
import com.example.memoboard.data.DBSectionRepository
import com.example.memoboard.data.LocalSectionProvider
import com.example.memoboard.data.LocalSectionRepository
import com.example.memoboard.data.MemoDatabase
import com.example.memoboard.data.SectionRepository

interface AppContainer {
    val sectionRepository: SectionRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val sectionRepository: SectionRepository by lazy {
        // DBSectionRepository(MemoDatabase.getDatabase(context).sectionDao())
        LocalSectionRepository(LocalSectionProvider)
    }
}
