package com.example.memoboard.ui.append

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

class MemoAppendViewModel(
    savedStateHandle: SavedStateHandle,
    private val memoRepository: MemoRepository
) : ViewModel() {

    private val memoId: Int = checkNotNull(savedStateHandle[AppendDestination.memoIdArg])

    val uiState: StateFlow<MemoAppendUiState> =
        memoRepository.getMemoById(memoId)
            .filterNotNull()
            .map { MemoAppendUiState(memo = it) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(TIMEOUT_MILLIS),
                initialValue = MemoAppendUiState()
            )

    fun appendMemo(
        changedMemoName: String,
        changedMemoContent: String,
        onNavigateBack: () -> Unit
    ) {
        if (changedMemoName.isNotEmpty() || changedMemoContent.isNotEmpty()) {
            uiState.value.memo.name = changedMemoName
            uiState.value.memo.content += System.lineSeparator() + changedMemoContent.trimIndent()

            viewModelScope.launch {
                memoRepository.updateMemo(uiState.value.memo)
            }
        }
        onNavigateBack()
    }

    companion object {
        private const val TIMEOUT_MILLIS = 5_000L
    }
}

data class MemoAppendUiState(
    val memo: Memo = Memo(),
)
