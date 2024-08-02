package com.example.memoboard.ui.edit

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memoboard.data.Memo
import com.example.memoboard.data.MemoRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class MemoEditViewModel(
    savedStateHandle: SavedStateHandle,
    private val memoRepository: MemoRepository
) : ViewModel() {

    private val memoId: Int = checkNotNull(savedStateHandle[EditDestination.memoIdArg])

    val uiState: StateFlow<MemoEditUiState> =
        memoRepository.getMemoById(memoId)
            .filterNotNull()
            .map { MemoEditUiState(memo = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = MemoEditUiState()
            )

    fun editMemo(
        changedMemoName: String,
        changedMemoContent: String,
    ) {
        if (changedMemoName.isNotEmpty()) {
            uiState.value.memo.name = changedMemoName
            uiState.value.memo.content = changedMemoContent
            uiState.value.memo.lastModifiedDate = LocalDateTime.now()

            viewModelScope.launch {
                memoRepository.updateMemo(uiState.value.memo)
            }
        }
    }

    fun deleteMemo() {
        viewModelScope.launch {
            memoRepository.deleteMemo(uiState.value.memo)
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class MemoEditUiState(
    val memo: Memo = Memo(),
)
