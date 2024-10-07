package com.jbjbjb.memoboard

import android.app.Application
import com.jbjbjb.memoboard.config.AppContainer
import com.jbjbjb.memoboard.config.AppDataContainer

class MemoBoardApplication : Application() {
    lateinit var container: AppContainer

    override fun onCreate() {
        super.onCreate()
        container = AppDataContainer(this)
    }
}
