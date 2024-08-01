package com.example.memoboard.ui.append

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
import com.example.memoboard.R
import com.example.memoboard.config.ViewModelProvider
import com.example.memoboard.ui.FlexibleTopBar
import com.example.memoboard.ui.common.MemoBoardTopAppBar
import com.example.memoboard.ui.common.MemoEditCard
import com.example.memoboard.ui.navigation.NavigationDestination

object AppendDestination : NavigationDestination {
    override val route = "append"
    override val titleRes = R.string.append_title
    const val memoIdArg = "memoId" // can be used as a handle for ViewModel
    val routeWithArgs = "$route/{$memoIdArg}"
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoAppendScreen(
    onNavigateBack: () -> Unit = {},
    viewModel: MemoAppendViewModel = viewModel(factory = ViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val appendUiState by viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    val memo = appendUiState.memo
    var memoName by remember { mutableStateOf("") }
    var memoContent by remember { mutableStateOf("") }

    LaunchedEffect(memo.name, memo.content) {
        memoName = memo.name
        memoContent = "" // since append
    } // kinda like useEffect?

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            FlexibleTopBar(
                modifier = Modifier.padding(top = 16.dp),
                scrollBehavior = scrollBehavior,
                content = {
                    MemoBoardTopAppBar(
                        title = stringResource(id = AppendDestination.titleRes),
                        canNavigateBack = true,
                        navigateUp = {
                            viewModel.appendMemo(
                                changedMemoName = memoName,
                                changedMemoContent = memoContent,
                                onNavigateBack = onNavigateBack
                            )
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
                .padding(horizontal = 16.dp, vertical = 8.dp)
        )
    }
}
