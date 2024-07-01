package com.example.memoboard

import androidx.compose.runtime.Composable
import androidx.compose.material3.Text

import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.memoboard.ui.home.HomeScreen

@Composable
fun MemoBoardScreen(navController: NavHostController = rememberNavController()) {
    HomeScreen()
}
