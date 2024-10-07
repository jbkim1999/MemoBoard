package com.jbjbjb.memoboard.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbjbjb.memoboard.data.Memo
import com.jbjbjb.memoboard.data.MemoRepository
import kotlinx.coroutines.flow.SharingStarted

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    memoRepository: MemoRepository
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> =
        memoRepository.getAllMemos()
            .map {
                HomeUiState(it)
            }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = HomeUiState(emptyList())
            )
}

data class HomeUiState(
    val memos: List<Memo>
)
