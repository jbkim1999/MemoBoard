package com.jbjbjb.memoboard.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.jbjbjb.memoboard.ui.append.AppendDestination
import com.jbjbjb.memoboard.ui.append.MemoAppendScreen
import com.jbjbjb.memoboard.ui.create.CreateDestination
import com.jbjbjb.memoboard.ui.create.MemoCreateScreen
import com.jbjbjb.memoboard.ui.edit.EditDestination
import com.jbjbjb.memoboard.ui.edit.MemoEditScreen
import com.jbjbjb.memoboard.ui.home.HomeDestination
import com.jbjbjb.memoboard.ui.home.HomeScreen

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
                onMemoCreate = { navController.navigate(CreateDestination.route) },
                onMemoAppend = { navController.navigate("${AppendDestination.route}/$it") },
                onMemoEdit = { navController.navigate("${EditDestination.route}/$it") }
            )
        }

        composable(route = CreateDestination.route) {
            MemoCreateScreen(
                onNavigateBack = { navController.popBackStack() }
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
