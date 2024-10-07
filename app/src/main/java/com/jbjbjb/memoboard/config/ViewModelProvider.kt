package com.jbjbjb.memoboard.config

import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.createSavedStateHandle
import androidx.lifecycle.viewmodel.CreationExtras
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.jbjbjb.memoboard.MemoBoardApplication
import com.jbjbjb.memoboard.ui.append.MemoAppendViewModel
import com.jbjbjb.memoboard.ui.create.MemoCreateViewModel
import com.jbjbjb.memoboard.ui.edit.MemoEditViewModel
import com.jbjbjb.memoboard.ui.home.HomeViewModel

object ViewModelProvider {
    val Factory = viewModelFactory {
        initializer {
            HomeViewModel(
                memoBoardApplication().container.memoRepository
            )
        }
        initializer {
            MemoCreateViewModel(
                memoBoardApplication().container.memoRepository
            )
        }
        initializer {
            MemoAppendViewModel(
                this.createSavedStateHandle(),
                memoBoardApplication().container.memoRepository
            )
        }
        initializer {
            MemoEditViewModel(
                this.createSavedStateHandle(),
                memoBoardApplication().container.memoRepository
            )
        }
    }
}

fun CreationExtras.memoBoardApplication(): MemoBoardApplication =
    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as MemoBoardApplication)
