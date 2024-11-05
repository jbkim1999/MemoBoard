package com.jbjbjb.memoboard.ui.edit

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
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

object EditDestination : NavigationDestination {
    override val route = "edit"
    override val titleRes = R.string.edit_title
    const val memoIdArg = "memoId" // can be used as a handle for ViewModel
    val routeWithArgs = "$route/{$memoIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoEditScreen(
    onNavigateBack: () -> Unit = {},
    viewModel: MemoEditViewModel = viewModel(factory = ViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val editUiState by viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val memo = editUiState.memo
    var memoName by remember { mutableStateOf("") }
    var originalMemoContent by remember { mutableStateOf("") }
    var memoContent by remember { mutableStateOf("") }

    LaunchedEffect(memo.name, memo.content) {
        memoName = memo.name
        originalMemoContent = memo.content
        memoContent = memo.content
    } // kinda like useEffect?

    BackHandler(onBack = {
        viewModel.editMemo(
            changedMemoName = memoName,
            changedMemoContent = memoContent,
        )
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
                        title = stringResource(id = EditDestination.titleRes),
                        titleClickedActionName = stringResource(R.string.delete_action),
                        titleClickedAction = {
                            viewModel.deleteMemo()
                            onNavigateBack()
                        },
                        canNavigateBack = true,
                        navigateUp = {
                            viewModel.editMemo(
                                changedMemoName = memoName,
                                changedMemoContent = memoContent,
                            )
                            onNavigateBack()
                        }
                    )
                }
            )
        }
    ) { innerPadding ->
        MemoEditCard(
            memoName = memoName,
            onMemoNameChange = { memoName = it },
            memoContent = memoContent,
            onMemoContentChange = { memoContent = it },
            modifier = Modifier
                .padding(innerPadding)
                .padding(horizontal = 16.dp, vertical = 8.dp),
            originalMemoContent = originalMemoContent
        )
    }
}
