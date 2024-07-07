package com.example.memoboard.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.memoboard.data.Section
import com.example.memoboard.data.SectionRepository
import kotlinx.coroutines.flow.SharingStarted

import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class HomeViewModel(
    private val sectionRepository: SectionRepository
) : ViewModel() {

    val uiState: StateFlow<HomeUiState> =
        sectionRepository.getAllSections().map {
            HomeUiState(it)
        }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(5000),
                initialValue = HomeUiState(emptyList())
            )
}

data class HomeUiState(
    val sections: List<Section>
)
