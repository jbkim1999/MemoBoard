package com.jbjbjb.memoboard.ui.common

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.jbjbjb.memoboard.data.LocalMemoProvider
import com.jbjbjb.memoboard.ui.theme.MemoBoardTheme
import dev.jeziellago.compose.markdowntext.MarkdownText

@Composable
fun MemoEditCard(
    memoName: String,
    onMemoNameChange: (String) -> Unit,
    memoContent: String,
    onMemoContentChange: (String) -> Unit,
    modifier: Modifier = Modifier,
    originalMemoContent: String = ""
) {
    var isWrite by remember { mutableStateOf(true) }

    // change this and memoContent
    var textFieldValue by remember { mutableStateOf(TextFieldValue(text = "")) }
    val focusRequester = remember { FocusRequester() }

    // logic to initialize textFieldValue only once when there is originalMemoContent
    LaunchedEffect(originalMemoContent) {
        textFieldValue = TextFieldValue(
            text = memoContent,
        )
    }

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
                    .clickable(onClick = {
                        focusRequester.requestFocus()
                        textFieldValue = TextFieldValue(
                            text = memoContent,
                            selection = TextRange(memoContent.length)
                        )
                    })
            ) {
                if (isWrite) {
                    TextField(
                        value = textFieldValue,
                        onValueChange = {
                            textFieldValue = it
                            onMemoContentChange(it.text)
                        },
                        colors = TextFieldDefaults.colors(
                            focusedContainerColor = CardDefaults.outlinedCardColors().containerColor,
                            unfocusedContainerColor = CardDefaults.outlinedCardColors().containerColor,
                            focusedIndicatorColor = Color.Transparent,
                            unfocusedIndicatorColor = Color.Transparent
                        ),
                        modifier = Modifier
                            .fillMaxWidth()
                            .focusRequester(focusRequester)
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
