package com.example.memoboard.config

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.memoboard.MemoBoardApplication
import com.example.memoboard.ui.home.HomeViewModel

object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                memoBoardApplication().container.sectionRepository
            )
        }
    }
}

fun CreationExtras.memoBoardApplication(): MemoBoardApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MemoBoardApplication)
