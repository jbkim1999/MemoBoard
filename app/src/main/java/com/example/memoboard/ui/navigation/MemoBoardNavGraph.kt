package com.example.memoboard.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.memoboard.ui.append.AppendDestination
import com.example.memoboard.ui.edit.EditDestination
import com.example.memoboard.ui.home.HomeDestination
import com.example.memoboard.ui.home.HomeScreen

@Composable
fun MemoBoardNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = HomeDestination.route,
        modifier = modifier
    ) {
        // route is just a string
        composable(route = HomeDestination.route) {
            HomeScreen(
                onSectionAppend = { navController.navigate("${AppendDestination.route}/$it") },
                onSectionEdit = { navController.navigate("${EditDestination.route}/$it") }
            )
        }
    }
}
