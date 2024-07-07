package com.example.memoboard

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.memoboard.ui.home.HomeScreen
import com.example.memoboard.ui.theme.MemoBoardTheme

@Composable
fun MemoBoardScreen(navController: NavHostController = rememberNavController()) {
    HomeScreen()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MemoBoardTopAppBar(
    title: String,
    canNavigateBack: Boolean,
    navigateUp: () -> Unit = {},
    titleClickedActionName: String = "",
    titleClickedAction: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    var titleClicked by remember {
        mutableStateOf(true)
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMediumLow
                )
            ),
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedCard(
            modifier = modifier
                .width(240.dp),
            shape = RoundedCornerShape(16.dp),
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextButton(
                        enabled = titleClickedActionName != "",
                        onClick = { titleClicked = !titleClicked },
                        colors = ButtonDefaults.buttonColors(
                            // Match with Card
                            containerColor = CardDefaults.outlinedCardColors().containerColor,
                            contentColor = CardDefaults.outlinedCardColors().contentColor,
                            disabledContainerColor = CardDefaults.outlinedCardColors().containerColor,
                            disabledContentColor = CardDefaults.outlinedCardColors().contentColor,
                        )
                    ) {
                        Text(
                            text = title,
                            style = MaterialTheme.typography.titleMedium,
                        )
                    }
                }

                if (titleClicked) {
                    Card(
                        shape = RoundedCornerShape(8.dp),
                        modifier = Modifier
                            .width(200.dp)
                            .padding(bottom = 8.dp)
                            .clickable(
                                onClick = titleClickedAction
                            )
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth(),
                            horizontalArrangement = Arrangement.Start
                        ) {
                            Text(
                                modifier = Modifier.padding(start = 16.dp, top = 4.dp, bottom = 4.dp),
                                text = titleClickedActionName,
                                style = MaterialTheme.typography.bodyLarge,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
@Preview
fun MemoBoardTopAppBarPreview() {
    MemoBoardTheme {
        MemoBoardTopAppBar(
            title = "Hello World",
            canNavigateBack = false
        )
    }
}
