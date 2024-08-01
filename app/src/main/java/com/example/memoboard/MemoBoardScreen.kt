package com.example.memoboard

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.memoboard.ui.navigation.MemoBoardNavHost

@Composable
fun MemoBoardScreen(navController: NavHostController = rememberNavController()) {
    MemoBoardNavHost(
        navController = rememberNavController(),
        modifier = Modifier
    )
}
