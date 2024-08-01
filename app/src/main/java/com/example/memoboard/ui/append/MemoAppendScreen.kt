package com.example.memoboard.ui.append

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.memoboard.R
import com.example.memoboard.config.ViewModelProvider
import com.example.memoboard.data.LocalMemoProvider
import com.example.memoboard.ui.FlexibleTopBar
import com.example.memoboard.ui.common.MemoBoardTopAppBar
import com.example.memoboard.ui.navigation.NavigationDestination
import com.example.memoboard.ui.theme.MemoBoardTheme
import dev.jeziellago.compose.markdowntext.MarkdownText

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoEditCard(
    memoName: String,
    onMemoNameChange: (String) -> Unit,
    memoContent: String,
    onMemoContentChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    var isWrite by remember { mutableStateOf(true) }

    Column(
        modifier = modifier
    ) {
        OutlinedCard(
            border = BorderStroke(1.5.dp, CardDefaults.outlinedCardBorder().brush),
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
            ) {
                TextField(
                    value = memoName,
                    onValueChange = { onMemoNameChange(it) },
                    colors = TextFieldDefaults.colors(
                        focusedContainerColor = CardDefaults.outlinedCardColors().containerColor,
                        unfocusedContainerColor = CardDefaults.outlinedCardColors().containerColor,
                        focusedIndicatorColor = Color.Transparent,
                        unfocusedIndicatorColor = Color.Transparent
                    ),
                    modifier = Modifier.fillMaxWidth()
                )
            }
        }
        OutlinedCard(
            border = BorderStroke(1.5.dp, CardDefaults.outlinedCardBorder().brush),
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                horizontalArrangement = Arrangement.SpaceAround,
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                TextButton(
                    onClick = { isWrite = true }
                ) {
                    Text(
                        fontWeight = if (isWrite) FontWeight.ExtraBold else FontWeight.Normal,
                        text = "What you write"
                    )
                }
                TextButton(
                    onClick = { isWrite = false }
                ) {
                    Text(
                        fontWeight = if (!isWrite) FontWeight.ExtraBold else FontWeight.Normal,
                        text = "What you see"
                    )
                }
            }
            HorizontalDivider()
            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .fillMaxHeight()
            ) {
                if (isWrite) {
                    TextField(
                        value = memoContent,
                        onValueChange = { onMemoContentChange(it) },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = CardDefaults.outlinedCardColors().containerColor,
                            unfocusedContainerColor = CardDefaults.outlinedCardColors().containerColor,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier.fillMaxWidth()
                    )
                } else {
                    MarkdownText(
                        markdown = memoContent,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun MemoEditCardPreview() {
    MemoBoardTheme {
        MemoEditCard(
            memoName = LocalMemoProvider.getAllMemos()[1].name,
            onMemoNameChange = {},
            memoContent = LocalMemoProvider.getAllMemos()[1].content,
            onMemoContentChange = {}
        )
    }
}
