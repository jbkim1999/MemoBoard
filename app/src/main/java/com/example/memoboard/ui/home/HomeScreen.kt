package com.example.memoboard.ui.home

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.memoboard.MemoBoardTopAppBar
import com.example.memoboard.R
import com.example.memoboard.config.ViewModelProvider
import com.example.memoboard.data.LocalMemoRepository
import com.example.memoboard.data.LocalMemoProvider
import com.example.memoboard.data.Memo
import com.example.memoboard.ui.FlexibleTopBar
import com.example.memoboard.ui.navigation.NavigationDestination
import com.example.memoboard.ui.theme.MemoBoardTheme
import dev.jeziellago.compose.markdowntext.MarkdownText
import kotlinx.coroutines.flow.first

object HomeDestination : NavigationDestination {
    override val route = "home"
    override val titleRes = R.string.app_name
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    onMemoAppend: (Int) -> Unit = {},
    onMemoEdit: (Int) -> Unit = {},
    viewModel: HomeViewModel = viewModel(factory = ViewModelProvider.Factory),
    modifier: Modifier = Modifier
) {
    val homeUiState by viewModel.uiState.collectAsState()
    val scrollBehavior = TopAppBarDefaults.enterAlwaysScrollBehavior()

    Scaffold(
        modifier = modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            FlexibleTopBar(
                modifier = Modifier.padding(top = 16.dp),
                scrollBehavior = scrollBehavior,
                content = {
                    MemoBoardTopAppBar(
                        title = "Memo Board",
                        canNavigateBack = false,
                    )
                }
            )
        }
    ) { innerPadding ->

        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            items(items = homeUiState.memos, key = { it.id }) { memo ->
                MarkdownCard(
                    memo = memo,
                    onMemoAppend = onMemoAppend,
                    onMemoEdit = onMemoEdit,
                    modifier = Modifier
                        .padding(horizontal = 16.dp, vertical = 8.dp)
                )
            }
        }
    }
}

@Composable
fun MarkdownCard(
    memo: Memo,
    onMemoAppend: (Int) -> Unit = {},
    onMemoEdit: (Int) -> Unit = {},
    onMemoClick: () -> Unit = {}, // Not yet supported
    modifier: Modifier = Modifier,
) {
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = modifier
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioLowBouncy,
                    stiffness = Spring.StiffnessMedium
                )
            )
    ) {
        OutlinedCard(
            border = BorderStroke(1.5.dp, CardDefaults.outlinedCardBorder().brush),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = memo.name,
                    style = MaterialTheme.typography.titleMedium,
                    modifier = Modifier
                        .padding(start = 16.dp)
                )
                Spacer(
                    modifier = Modifier.weight(1f)
                )
                IconButton(
                    onClick = { onMemoAppend(memo.id) },
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_add_24),
                        contentDescription = "append icon",
                    )
                }
                IconButton(
                    onClick = { onMemoEdit(memo.id) }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.baseline_edit_24),
                        contentDescription = "edit icon",
                    )
                }
                IconButton(
                    onClick = { isExpanded = !isExpanded },
                    modifier = Modifier.padding(end = 8.dp)
                ) {
                    Icon(
                        painter =
                        if (!isExpanded)
                            painterResource(id = R.drawable.baseline_arrow_drop_down_40)
                        else
                            painterResource(id = R.drawable.baseline_arrow_drop_up_40),
                        contentDescription = "expand/collapse icon",
                    )
                }
            }
        }
        if (isExpanded) {
            OutlinedCard(
                border = BorderStroke(1.5.dp, CardDefaults.outlinedCardBorder().brush),
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                MarkdownText(
                    markdown = memo.content.trimIndent(),
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}

@Preview
@Composable
fun MarkdownCardPreview() {
    MemoBoardTheme {
        MarkdownCard(memo = LocalMemoProvider.getAllMemos()[1])
    }
}
