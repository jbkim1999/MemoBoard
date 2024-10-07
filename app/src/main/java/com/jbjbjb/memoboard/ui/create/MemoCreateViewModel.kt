package com.jbjbjb.memoboard.ui.create

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jbjbjb.memoboard.data.Memo
import com.jbjbjb.memoboard.data.MemoRepository
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class MemoCreateViewModel(
    private val memoRepository: MemoRepository
) : ViewModel() {

    var uiState by mutableStateOf(MemoCreateUiState())

    fun createMemo() {
        if (uiState.memo.name.isNotEmpty()) {
            uiState.memo.lastModifiedDate = LocalDateTime.now()

            viewModelScope.launch {
                memoRepository.insertMemo(uiState.memo)
            }
        }
    }
}

data class MemoCreateUiState(
    val memo: Memo = Memo(),
)
