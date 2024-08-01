package com.example.memoboard.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.memoboard.ui.append.AppendDestination
import com.example.memoboard.ui.append.MemoAppendScreen
import com.example.memoboard.ui.edit.EditDestination
import com.example.memoboard.ui.edit.MemoEditScreen
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
                onMemoAppend = { navController.navigate("${AppendDestination.route}/$it") },
                onMemoEdit = { navController.navigate("${EditDestination.route}/$it") }
            )
        }

        composable(
            route = AppendDestination.routeWithArgs,
            arguments = listOf(navArgument(AppendDestination.memoIdArg) {
                type = NavType.IntType
            })
        ) {
            MemoAppendScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = EditDestination.routeWithArgs,
            arguments = listOf(navArgument(EditDestination.memoIdArg) {
                type = NavType.IntType
            })
        ) {
            MemoEditScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
