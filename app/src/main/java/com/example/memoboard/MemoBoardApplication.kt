package com.example.memoboard

import android.app.Application
import com.example.memoboard.config.AppContainer
import com.example.memoboard.config.AppDataContainer

class MemoBoardApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
