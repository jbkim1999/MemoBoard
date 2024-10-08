package com.jbjbjb.memoboard.ui.create

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.jbjbjb.memoboard.R
import com.jbjbjb.memoboard.config.ViewModelProvider
import com.jbjbjb.memoboard.ui.FlexibleTopBar
import com.jbjbjb.memoboard.ui.common.MemoBoardTopAppBar
import com.jbjbjb.memoboard.ui.common.MemoEditCard
import com.jbjbjb.memoboard.ui.navigation.NavigationDestination

object CreateDestination : NavigationDestination {
    override val route = "create"
    override val titleRes = R.string.create_title
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoCreateScreen(
    onNavigateBack: () -> Unit = {},
    viewModel: MemoCreateViewModel = viewModel(factory = ViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()
    BackHandler(onBack = {
        viewModel.createMemo()
        onNavigateBack()
    })

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            FlexibleTopBar(
                modifier = Modifier.padding(top = 16.dp),
                scrollBehavior = scrollBehavior,
                content = {
                    MemoBoardTopAppBar(
                        title = stringResource(id = CreateDestination.titleRes),
                        canNavigateBack = true,
                        navigateUp = {
                            viewModel.createMemo()
                            onNavigateBack()
                        }
                    )
                }
            )
        }
    ) { innerPadding ->
        MemoEditCard(
            memoName = viewModel.uiState.memo.name,
            onMemoNameChange = {
                viewModel.uiState =
                    viewModel.uiState.copy(memo = viewModel.uiState.memo.copy(name = it))
            },
            memoContent = viewModel.uiState.memo.content,
            onMemoContentChange = {
                viewModel.uiState =
                    viewModel.uiState.copy(memo = viewModel.uiState.memo.copy(content = it))
            },
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}
